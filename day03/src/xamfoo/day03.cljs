(ns xamfoo.day03
  (:require
    [xamfoo.cli :as cli]
    [xamfoo.day03.fabric :as fabric]
    [xamfoo.day03.part1 :as part1]
    [xamfoo.day03.part2 :as part2]))

(defonce input (cli/read-file))
(defonce claimed-fabric (fabric/make input))
(defonce part1-ans (part1/overlap claimed-fabric))
(defonce part2-ans (part2/find-intact claimed-fabric))

(defn main! []
  (println "Part 1 - Overlap:" part1-ans)
  (println "Part 2 - Intact:" part2-ans))
