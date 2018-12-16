(ns xamfoo.day02
  (:require
    [xamfoo.cli :as cli]
    [xamfoo.day02.part1 :as part1]
    [xamfoo.day02.part2 :as part2]))

(defonce input (cli/read-file))

(defonce part1-ans (part1/checksum input))

(defonce part2-ans (part2/common-letters-of-match input))

(defn main! []
  (println (str "Part 1 - Checksum: " part1-ans))
  (println (str "Part 2 - Common letters: " (js/JSON.stringify part2-ans)))
