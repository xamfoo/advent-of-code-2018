(ns xamfoo.day01
  (:require
    [xamfoo.cli :as cli]
    [xamfoo.day01.part1 :as part1]
    [xamfoo.day01.part2 :as part2]))

(defonce input (map #(js/parseInt % 10) (cli/read-file)))

(defonce part1-ans (part1/sum input))

(defonce part2-ans (part2/find-freq input))

(defn main! []
  (println "Part 1:" part1-ans)
  (println "Part 2:" part2-ans))
