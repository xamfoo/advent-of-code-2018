(ns xamfoo.day04
  (:require
    [xamfoo.cli :as cli]
    [xamfoo.day04.core :as core]
    [xamfoo.day04.part1 :as part1]
    [xamfoo.day04.part2 :as part2]))

(defonce input (cli/read-file))
(defonce guard-data (core/make-data input))
(defonce part1-ans (part1/id-x-min guard-data))
(defonce part2-ans (part2/id-x-min guard-data))

(defn main! []
  (println "Part 1 - ID of sleepiest guard * sleepiest minute:" part1-ans)
  (println "Part 2 - ID of guard sleeping most on the same minute * minute:" part2-ans))
