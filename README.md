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
sbt> jmh:run -i 2 -wi 2 -f2

[info] Benchmark                Mode  Cnt    Score     Error  Units
[info] Main_10_10_1000.route1  thrpt    4   10,608 ±   1,251  ops/s
[info] Main_10_10_1000.route2  thrpt    4    8,318 ±   8,986  ops/s
[info] Main_10_10_1000.route3  thrpt    4    9,796 ±   0,484  ops/s
[info] Main_10_10_1000.route4  thrpt    4    9,684 ±   0,543  ops/s
[info] Main_10_3_1000.route1   thrpt    4  271,409 ±  46,062  ops/s
[info] Main_10_3_1000.route2   thrpt    4  260,273 ±   5,966  ops/s
[info] Main_10_3_1000.route3   thrpt    4  271,997 ±  34,141  ops/s
[info] Main_10_3_1000.route4   thrpt    4  267,214 ±  18,336  ops/s
[info] Main_3_10_1000.route1   thrpt    4   24,494 ±   2,948  ops/s
[info] Main_3_10_1000.route2   thrpt    4   24,103 ±   1,478  ops/s
[info] Main_3_10_1000.route3   thrpt    4   23,899 ±   3,415  ops/s
[info] Main_3_10_1000.route4   thrpt    4   23,861 ±   1,138  ops/s
[info] Main_3_3_1000.route1    thrpt    4  737,946 ± 149,324  ops/s
[info] Main_3_3_1000.route2    thrpt    4  775,798 ±  60,308  ops/s
[info] Main_3_3_1000.route3    thrpt    4  748,306 ± 107,868  ops/s
[info] Main_3_3_1000.route4    thrpt    4  737,988 ± 197,609  ops/s

```

