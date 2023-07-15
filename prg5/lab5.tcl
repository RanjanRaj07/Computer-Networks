#simulator setup
set ns [new Simulator]
set nf [open lab5.nam w]
$ns namtrace-all $nf
set tf [open lab5.tr w]
$ns trace-all $tf

#node setting
set c1 [$ns node]
set ms [$ns node]
set bs1 [$ns node]
set bs2 [$ns node]
set c2 [$ns node]
$ns color 1 "red"
$ns color 2 "blue"
$c1 label "sender mobile node"
$c1 color "green"
$bs1 label "base station 1"
$ms label "mobile station"
$bs2 label "base station 2"
$c2 label "destination mobile node"
$c2 color "red"

#cell_topo procedure
proc cell_topo {} {
global ns c1 bs1 ms bs2 c2
$ns duplex-link $c1 $bs1 3Mbps 10ms DropTail
$ns duplex-link $bs1 $ms 1 1 RED
$ns duplex-link $ms $bs2 1 1 RED
$ns duplex-link $bs2 $c2 3Mbps 50ms DropTail
}

switch gsm {
gsm -
gprs -
umts {cell_topo}
}

#bandwidth setting
$ns bandwidth $bs1 $ms 9600 simplex
$ns bandwidth $ms $bs1 9600 simplex
$ns bandwidth $bs2 $ms 9600 simplex
$ns bandwidth $ms $bs2 9600 simplex
#delay
$ns delay $bs1 $ms .500 simplex
$ns delay $ms $bs1 .500 simplex
$ns delay $bs2 $ms .500 simplex
$ns delay $ms $bs2 .500 simplex
#queue-limit
$ns queue-limit $bs1 $ms 10
$ns queue-limit $ms $bs1 10
$ns queue-limit $bs2 $ms 10
$ns queue-limit $ms $bs2 10
#insert-delayer
$ns insert-delayer $ms $bs1 [new Delayer]
$ns insert-delayer $bs1 $ms [new Delayer]
$ns insert-delayer $ms $bs2 [new Delayer]
$ns insert-delayer $bs2 $ms [new Delayer]

#transport layer  protocol
set tcp [new Agent/TCP]
$ns attach-agent $c1 $tcp
set sink [new Agent/TCPSink]
$ns attach-agent $c2 $sink
set ftp [new Application/FTP]
$ftp attach-agent $tcp
$ns connect $tcp $sink

#end
proc End {} {
global ns tf nf
$ns flush-trace
close $tf
close $nf
exec awk -f Lab5.awk Lab5.tr &
exec xgraph -P -bar -x TIME -y DATA gsm.xg &
exit 0
}
$ns at 0.0 "$ftp start"
$ns at 10.0 "End"
$ns run
