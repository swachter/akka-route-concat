Measures different implementations of route concatentation operators
====================================================================

* route1: ops class is value class + lazy snd arg
* route2: ops class is value class
* route3: lazy snd arg
* route4: standard operator (`~`)

The test classes use routing trees with different depths and branching factors. All benchmark methods evaluate 1000 requests.

* Main_10_10: depth 10, branching factor 10
* Main_10_3: depth 10, branching factor 3
* Main_3_10: depth 3, branching factor 10
* Main_3_3: depth 3, branching factor 3

```
nice -10
...
sbt> jmh:run -i 3 -wi 3 -f3

[info] Benchmark           Mode  Cnt    Score    Error  Units
[info] Main_10_10.route1  thrpt    9  102,898 ±  2,407  ops/s
[info] Main_10_10.route2  thrpt    9   92,399 ± 13,969  ops/s
[info] Main_10_10.route3  thrpt    9   96,737 ±  2,400  ops/s
[info] Main_10_10.route4  thrpt    9   87,345 ± 20,320  ops/s
[info] Main_10_3.route1   thrpt    9  265,744 ±  8,338  ops/s
[info] Main_10_3.route2   thrpt    9  261,978 ±  5,713  ops/s
[info] Main_10_3.route3   thrpt    9  266,916 ±  6,041  ops/s
[info] Main_10_3.route4   thrpt    9  258,938 ±  6,512  ops/s
[info] Main_3_10.route1   thrpt    9  245,561 ±  6,930  ops/s
[info] Main_3_10.route2   thrpt    9  240,589 ±  6,620  ops/s
[info] Main_3_10.route3   thrpt    9  239,032 ±  3,725  ops/s
[info] Main_3_10.route4   thrpt    9  238,991 ±  4,340  ops/s
[info] Main_3_3.route1    thrpt    9  749,010 ± 20,641  ops/s
[info] Main_3_3.route2    thrpt    9  736,670 ± 28,980  ops/s
[info] Main_3_3.route3    thrpt    9  728,004 ± 37,302  ops/s
[info] Main_3_3.route4    thrpt    9  721,825 ± 49,598  ops/s
```

