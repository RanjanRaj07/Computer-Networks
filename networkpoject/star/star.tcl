set ns  [new Simulator]

$ns color 1 blue
$ns color 2 red

$ns rtproto DV

set nf [open star.nam w]
$ns namtrace-all $nf

proc finish {} {
        global ns nf
        $ns flush-trace
        close $nf
        exec nam star.nam
        exit 0
        }

#creating Nodes        
for {set i 0} {$i<7} {incr i} {
set n($i) [$ns node]
}

$n(0) color "green"
$n(0) label "router/switch"
$n(1) color "blue"
$n(1) label "tcp-client"
$n(4) color "blue"
$n(4) label "tcp-server"
$n(2) color "red"
$n(2) label "udp-client"
$n(5) color "red"
$n(5) label "udp-server"

#Creating Links
for {set i 1} {$i<7} {incr i} {
$ns duplex-link $n(0) $n($i) 512Kb 10ms DropTail
}

#Orienting The nodes
$ns duplex-link-op $n(0) $n(1) orient left-up
$ns duplex-link-op $n(0) $n(2) orient right-up
$ns duplex-link-op $n(0) $n(3) orient right
$ns duplex-link-op $n(0) $n(4) orient right-down
$ns duplex-link-op $n(0) $n(5) orient left-down
$ns duplex-link-op $n(0) $n(6) orient left

#TCP_Config
set tcp0 [new Agent/TCP]
$tcp0 set class_ 1
$ns attach-agent $n(1) $tcp0

set sink0 [new Agent/TCPSink]
$ns attach-agent $n(4) $sink0

$ns connect $tcp0 $sink0

#UDP_Config
set udp0 [new Agent/UDP]
$udp0 set class_ 2
$ns attach-agent $n(2) $udp0

set null0 [new Agent/Null]
$ns attach-agent $n(5) $null0

$ns connect $udp0 $null0

#CBR Config
set cbr0 [new Application/Traffic/CBR]
$cbr0 set rate_ 256Kb
$cbr0 attach-agent $udp0

#FTP Config
set ftp0 [new Application/FTP]
$ftp0 attach-agent $tcp0


#Scheduling Events
$ns rtmodel-at 0.5 down $n(0) $n(5)
$ns rtmodel-at 0.9 up $n(0) $n(5)

$ns rtmodel-at 0.7 down $n(0) $n(4)
$ns rtmodel-at 1.2 up $n(0) $n(4)

$ns at 0.1 "$ftp0 start"
$ns at 1.5 "$ftp0 stop"

$ns at 0.2 "$cbr0 start"
$ns at 1.3 "$cbr0 stop"

$ns at 2.0 "finish"
$ns run
