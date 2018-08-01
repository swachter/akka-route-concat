Measures different implementations of route concatentation operators
====================================================================

* route1: ops class is value class + by-name 2nd arg
* route2: ops class is value class
* route3: by-name 2nd arg
* route4: standard operator (`~`)

The test classes use routing trees with different depths and branching factors. All benchmark methods evaluate the same 1000 requests.

* Main_10_10: depth 10, branching factor 10
* Main_10_3: depth 10, branching factor 3
* Main_3_10: depth 3, branching factor 10
* Main_3_3: depth 3, branching factor 3

Summary of measurements: The concatenation combinator that is implemented by a value class and whose 2nd argument is by-name (i.e. route1) has the highest throughput and the smallest normalized allocation rate for all considered structures of routing trees. The difference in performance between the various concatenation operators gets more pronounced for deeper and broader routing trees. 

```
sbt> jmh:run -i 20 -wi 20 -f 5 -prof gc

[info] Benchmark                                                 Mode  Cnt         Score        Error   Units
[info] Main_10_10.route1                                        thrpt  100       103,701 ±      0,888   ops/s
[info] Main_10_10.route1:·gc.alloc.rate                         thrpt  100      2057,026 ±     20,666  MB/sec
[info] Main_10_10.route1:·gc.alloc.rate.norm                    thrpt  100  21859328,258 ± 312774,033    B/op
[info] Main_10_10.route1:·gc.churn.PS_Eden_Space                thrpt  100      2056,982 ±     22,827  MB/sec
[info] Main_10_10.route1:·gc.churn.PS_Eden_Space.norm           thrpt  100  21858908,157 ± 330129,189    B/op
[info] Main_10_10.route1:·gc.churn.PS_Survivor_Space            thrpt  100         0,058 ±      0,006  MB/sec
[info] Main_10_10.route1:·gc.churn.PS_Survivor_Space.norm       thrpt  100       614,713 ±     67,640    B/op
[info] Main_10_10.route1:·gc.count                              thrpt  100      3908,000               counts
[info] Main_10_10.route1:·gc.time                               thrpt  100      8111,000                   ms
[info] Main_10_10.route2                                        thrpt  100        98,102 ±      0,548   ops/s
[info] Main_10_10.route2:·gc.alloc.rate                         thrpt  100      1975,500 ±     18,693  MB/sec
[info] Main_10_10.route2:·gc.alloc.rate.norm                    thrpt  100  22174386,980 ± 172118,547    B/op
[info] Main_10_10.route2:·gc.churn.PS_Eden_Space                thrpt  100      1974,928 ±     21,086  MB/sec
[info] Main_10_10.route2:·gc.churn.PS_Eden_Space.norm           thrpt  100  22167679,306 ± 200365,928    B/op
[info] Main_10_10.route2:·gc.churn.PS_Survivor_Space            thrpt  100         0,057 ±      0,008  MB/sec
[info] Main_10_10.route2:·gc.churn.PS_Survivor_Space.norm       thrpt  100       644,582 ±     89,134    B/op
[info] Main_10_10.route2:·gc.count                              thrpt  100      3985,000               counts
[info] Main_10_10.route2:·gc.time                               thrpt  100      8093,000                   ms
[info] Main_10_10.route3                                        thrpt  100        98,758 ±      0,683   ops/s
[info] Main_10_10.route3:·gc.alloc.rate                         thrpt  100      2056,419 ±     19,626  MB/sec
[info] Main_10_10.route3:·gc.alloc.rate.norm                    thrpt  100  22934149,739 ± 215989,770    B/op
[info] Main_10_10.route3:·gc.churn.PS_Eden_Space                thrpt  100      2056,956 ±     21,251  MB/sec
[info] Main_10_10.route3:·gc.churn.PS_Eden_Space.norm           thrpt  100  22940285,974 ± 235904,347    B/op
[info] Main_10_10.route3:·gc.churn.PS_Survivor_Space            thrpt  100         0,061 ±      0,008  MB/sec
[info] Main_10_10.route3:·gc.churn.PS_Survivor_Space.norm       thrpt  100       678,121 ±     85,535    B/op
[info] Main_10_10.route3:·gc.count                              thrpt  100      4103,000               counts
[info] Main_10_10.route3:·gc.time                               thrpt  100      7996,000                   ms
[info] Main_10_10.route4                                        thrpt  100        95,469 ±      0,718   ops/s
[info] Main_10_10.route4:·gc.alloc.rate                         thrpt  100      2046,411 ±     22,436  MB/sec
[info] Main_10_10.route4:·gc.alloc.rate.norm                    thrpt  100  23608367,604 ± 250075,145    B/op
[info] Main_10_10.route4:·gc.churn.PS_Eden_Space                thrpt  100      2045,522 ±     24,831  MB/sec
[info] Main_10_10.route4:·gc.churn.PS_Eden_Space.norm           thrpt  100  23597954,055 ± 277438,358    B/op
[info] Main_10_10.route4:·gc.churn.PS_Survivor_Space            thrpt  100         0,056 ±      0,005  MB/sec
[info] Main_10_10.route4:·gc.churn.PS_Survivor_Space.norm       thrpt  100       650,544 ±     57,075    B/op
[info] Main_10_10.route4:·gc.count                              thrpt  100      3934,000               counts
[info] Main_10_10.route4:·gc.time                               thrpt  100      7921,000                   ms
[info] Main_10_3.route1                                         thrpt  100       276,687 ±      1,751   ops/s
[info] Main_10_3.route1:·gc.alloc.rate                          thrpt  100      2203,715 ±     24,021  MB/sec
[info] Main_10_3.route1:·gc.alloc.rate.norm                     thrpt  100   8771293,269 ±  88269,563    B/op
[info] Main_10_3.route1:·gc.churn.PS_Eden_Space                 thrpt  100      2203,589 ±     27,764  MB/sec
[info] Main_10_3.route1:·gc.churn.PS_Eden_Space.norm            thrpt  100   8770247,561 ±  98986,050    B/op
[info] Main_10_3.route1:·gc.churn.PS_Survivor_Space             thrpt  100         0,057 ±      0,008  MB/sec
[info] Main_10_3.route1:·gc.churn.PS_Survivor_Space.norm        thrpt  100       225,656 ±     29,681    B/op
[info] Main_10_3.route1:·gc.count                               thrpt  100      3888,000               counts
[info] Main_10_3.route1:·gc.time                                thrpt  100      8081,000                   ms
[info] Main_10_3.route2                                         thrpt  100       260,966 ±      1,557   ops/s
[info] Main_10_3.route2:·gc.alloc.rate                          thrpt  100      2173,671 ±     23,340  MB/sec
[info] Main_10_3.route2:·gc.alloc.rate.norm                     thrpt  100   9172793,507 ±  92358,812    B/op
[info] Main_10_3.route2:·gc.churn.PS_Eden_Space                 thrpt  100      2174,509 ±     24,736  MB/sec
[info] Main_10_3.route2:·gc.churn.PS_Eden_Space.norm            thrpt  100   9176377,768 ±  99100,884    B/op
[info] Main_10_3.route2:·gc.churn.PS_Survivor_Space             thrpt  100         0,061 ±      0,009  MB/sec
[info] Main_10_3.route2:·gc.churn.PS_Survivor_Space.norm        thrpt  100       258,399 ±     36,693    B/op
[info] Main_10_3.route2:·gc.count                               thrpt  100      4180,000               counts
[info] Main_10_3.route2:·gc.time                                thrpt  100      8111,000                   ms
[info] Main_10_3.route3                                         thrpt  100       266,405 ±      2,208   ops/s
[info] Main_10_3.route3:·gc.alloc.rate                          thrpt  100      2159,251 ±     31,108  MB/sec
[info] Main_10_3.route3:·gc.alloc.rate.norm                     thrpt  100   8926878,597 ± 121560,389    B/op
[info] Main_10_3.route3:·gc.churn.PS_Eden_Space                 thrpt  100      2159,586 ±     33,571  MB/sec
[info] Main_10_3.route3:·gc.churn.PS_Eden_Space.norm            thrpt  100   8927964,681 ± 129676,089    B/op
[info] Main_10_3.route3:·gc.churn.PS_Survivor_Space             thrpt  100         0,058 ±      0,006  MB/sec
[info] Main_10_3.route3:·gc.churn.PS_Survivor_Space.norm        thrpt  100       240,967 ±     25,562    B/op
[info] Main_10_3.route3:·gc.count                               thrpt  100      3858,000               counts
[info] Main_10_3.route3:·gc.time                                thrpt  100      8076,000                   ms
[info] Main_10_3.route4                                         thrpt  100       262,253 ±      2,273   ops/s
[info] Main_10_3.route4:·gc.alloc.rate                          thrpt  100      2187,327 ±     31,509  MB/sec
[info] Main_10_3.route4:·gc.alloc.rate.norm                     thrpt  100   9184644,951 ± 109216,360    B/op
[info] Main_10_3.route4:·gc.churn.PS_Eden_Space                 thrpt  100      2187,313 ±     33,576  MB/sec
[info] Main_10_3.route4:·gc.churn.PS_Eden_Space.norm            thrpt  100   9184199,735 ± 116257,935    B/op
[info] Main_10_3.route4:·gc.churn.PS_Survivor_Space             thrpt  100         0,057 ±      0,008  MB/sec
[info] Main_10_3.route4:·gc.churn.PS_Survivor_Space.norm        thrpt  100       238,706 ±     32,782    B/op
[info] Main_10_3.route4:·gc.count                               thrpt  100      3947,000               counts
[info] Main_10_3.route4:·gc.time                                thrpt  100      7995,000                   ms
[info] Main_3_10.route1                                         thrpt  100       252,744 ±      5,104   ops/s
[info] Main_3_10.route1:·gc.alloc.rate                          thrpt  100      1511,988 ±     43,836  MB/sec
[info] Main_3_10.route1:·gc.alloc.rate.norm                     thrpt  100   6578461,605 ±  55659,726    B/op
[info] Main_3_10.route1:·gc.churn.PS_Eden_Space                 thrpt  100      1512,040 ±     44,517  MB/sec
[info] Main_3_10.route1:·gc.churn.PS_Eden_Space.norm            thrpt  100   6578633,653 ±  64349,634    B/op
[info] Main_3_10.route1:·gc.churn.PS_Survivor_Space             thrpt  100         0,057 ±      0,006  MB/sec
[info] Main_3_10.route1:·gc.churn.PS_Survivor_Space.norm        thrpt  100       247,933 ±     24,795    B/op
[info] Main_3_10.route1:·gc.count                               thrpt  100      3796,000               counts
[info] Main_3_10.route1:·gc.time                                thrpt  100      7759,000                   ms
[info] Main_3_10.route2                                         thrpt  100       242,249 ±      1,921   ops/s
[info] Main_3_10.route2:·gc.alloc.rate                          thrpt  100      1487,085 ±     11,897  MB/sec
[info] Main_3_10.route2:·gc.alloc.rate.norm                     thrpt  100   6762573,651 ±  67833,406    B/op
[info] Main_3_10.route2:·gc.churn.PS_Eden_Space                 thrpt  100      1488,032 ±     13,450  MB/sec
[info] Main_3_10.route2:·gc.churn.PS_Eden_Space.norm            thrpt  100   6766868,380 ±  73489,009    B/op
[info] Main_3_10.route2:·gc.churn.PS_Survivor_Space             thrpt  100         0,055 ±      0,007  MB/sec
[info] Main_3_10.route2:·gc.churn.PS_Survivor_Space.norm        thrpt  100       250,987 ±     35,567    B/op
[info] Main_3_10.route2:·gc.count                               thrpt  100      3803,000               counts
[info] Main_3_10.route2:·gc.time                                thrpt  100      7876,000                   ms
[info] Main_3_10.route3                                         thrpt  100       234,589 ±      2,025   ops/s
[info] Main_3_10.route3:·gc.alloc.rate                          thrpt  100      1495,278 ±     10,269  MB/sec
[info] Main_3_10.route3:·gc.alloc.rate.norm                     thrpt  100   7023245,301 ±  76135,329    B/op
[info] Main_3_10.route3:·gc.churn.PS_Eden_Space                 thrpt  100      1495,424 ±     12,456  MB/sec
[info] Main_3_10.route3:·gc.churn.PS_Eden_Space.norm            thrpt  100   7023936,737 ±  83124,432    B/op
[info] Main_3_10.route3:·gc.churn.PS_Survivor_Space             thrpt  100         0,058 ±      0,007  MB/sec
[info] Main_3_10.route3:·gc.churn.PS_Survivor_Space.norm        thrpt  100       272,276 ±     33,876    B/op
[info] Main_3_10.route3:·gc.count                               thrpt  100      3856,000               counts
[info] Main_3_10.route3:·gc.time                                thrpt  100      7756,000                   ms
[info] Main_3_10.route4                                         thrpt  100       235,708 ±      1,600   ops/s
[info] Main_3_10.route4:·gc.alloc.rate                          thrpt  100      1474,738 ±     20,838  MB/sec
[info] Main_3_10.route4:·gc.alloc.rate.norm                     thrpt  100   6887997,144 ±  68871,388    B/op
[info] Main_3_10.route4:·gc.churn.PS_Eden_Space                 thrpt  100      1474,571 ±     21,725  MB/sec
[info] Main_3_10.route4:·gc.churn.PS_Eden_Space.norm            thrpt  100   6887318,782 ±  75379,267    B/op
[info] Main_3_10.route4:·gc.churn.PS_Survivor_Space             thrpt  100         0,053 ±      0,006  MB/sec
[info] Main_3_10.route4:·gc.churn.PS_Survivor_Space.norm        thrpt  100       248,969 ±     26,009    B/op
[info] Main_3_10.route4:·gc.count                               thrpt  100      3911,000               counts
[info] Main_3_10.route4:·gc.time                                thrpt  100      7728,000                   ms
[info] Main_3_3.route1                                          thrpt  100       751,421 ±      5,125   ops/s
[info] Main_3_3.route1:·gc.alloc.rate                           thrpt  100      2127,876 ±     25,237  MB/sec
[info] Main_3_3.route1:·gc.alloc.rate.norm                      thrpt  100   3119017,639 ±  37677,845    B/op
[info] Main_3_3.route1:·gc.churn.PS_Eden_Space                  thrpt  100      2128,275 ±     28,269  MB/sec
[info] Main_3_3.route1:·gc.churn.PS_Eden_Space.norm             thrpt  100   3119468,133 ±  40874,390    B/op
[info] Main_3_3.route1:·gc.churn.PS_Survivor_Space              thrpt  100         0,059 ±      0,006  MB/sec
[info] Main_3_3.route1:·gc.churn.PS_Survivor_Space.norm         thrpt  100        86,008 ±      9,006    B/op
[info] Main_3_3.route1:·gc.count                                thrpt  100      3937,000               counts
[info] Main_3_3.route1:·gc.time                                 thrpt  100      8119,000                   ms
[info] Main_3_3.route2                                          thrpt  100       743,140 ±     11,232   ops/s
[info] Main_3_3.route2:·gc.alloc.rate                           thrpt  100      2103,513 ±     22,061  MB/sec
[info] Main_3_3.route2:·gc.alloc.rate.norm                      thrpt  100   3120091,333 ±  33848,125    B/op
[info] Main_3_3.route2:·gc.churn.PS_Eden_Space                  thrpt  100      2104,212 ±     23,442  MB/sec
[info] Main_3_3.route2:·gc.churn.PS_Eden_Space.norm             thrpt  100   3121102,184 ±  35548,718    B/op
[info] Main_3_3.route2:·gc.churn.PS_Survivor_Space              thrpt  100         0,057 ±      0,008  MB/sec
[info] Main_3_3.route2:·gc.churn.PS_Survivor_Space.norm         thrpt  100        83,702 ±     10,850    B/op
[info] Main_3_3.route2:·gc.count                                thrpt  100      3852,000               counts
[info] Main_3_3.route2:·gc.time                                 thrpt  100      8025,000                   ms
[info] Main_3_3.route3                                          thrpt  100       749,838 ±      6,194   ops/s
[info] Main_3_3.route3:·gc.alloc.rate                           thrpt  100      2180,144 ±     26,887  MB/sec
[info] Main_3_3.route3:·gc.alloc.rate.norm                      thrpt  100   3202387,262 ±  36512,442    B/op
[info] Main_3_3.route3:·gc.churn.PS_Eden_Space                  thrpt  100      2180,853 ±     28,431  MB/sec
[info] Main_3_3.route3:·gc.churn.PS_Eden_Space.norm             thrpt  100   3203435,341 ±  39014,122    B/op
[info] Main_3_3.route3:·gc.churn.PS_Survivor_Space              thrpt  100         0,078 ±      0,014  MB/sec
[info] Main_3_3.route3:·gc.churn.PS_Survivor_Space.norm         thrpt  100       115,248 ±     20,155    B/op
[info] Main_3_3.route3:·gc.count                                thrpt  100      4605,000               counts
[info] Main_3_3.route3:·gc.time                                 thrpt  100      8014,000                   ms
[info] Main_3_3.route4                                          thrpt  100       750,070 ±      6,757   ops/s
[info] Main_3_3.route4:·gc.alloc.rate                           thrpt  100      2194,746 ±     17,801  MB/sec
[info] Main_3_3.route4:·gc.alloc.rate.norm                      thrpt  100   3222840,058 ±  19668,553    B/op
[info] Main_3_3.route4:·gc.churn.PS_Eden_Space                  thrpt  100      2194,557 ±     19,783  MB/sec
[info] Main_3_3.route4:·gc.churn.PS_Eden_Space.norm             thrpt  100   3222592,867 ±  23818,782    B/op
[info] Main_3_3.route4:·gc.churn.PS_Survivor_Space              thrpt  100         0,057 ±      0,006  MB/sec
[info] Main_3_3.route4:·gc.churn.PS_Survivor_Space.norm         thrpt  100        84,110 ±      8,981    B/op
[info] Main_3_3.route4:·gc.count                                thrpt  100      3854,000               counts
[info] Main_3_3.route4:·gc.time                                 thrpt  100      8004,000                   ms
```