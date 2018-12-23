(ns xamfoo.day05
  (:require
    [xamfoo.cli :as cli]
    [xamfoo.day05.part1 :as part1]
    [xamfoo.day05.part2 :as part2]))

(def input (cli/read-file))
(def part1-ans (part1/stable-len input))
(def part2-ans (part2/shortest-len input))

(defn main! []
  (println "Part 1 - Stable polymer length:" part1-ans)
  (println "Part 2 - Shortest polymer length:" part2-ans))
