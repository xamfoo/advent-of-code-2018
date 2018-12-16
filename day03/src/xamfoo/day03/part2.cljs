(ns xamfoo.day03.part2
  (:require
    [clojure.set]))

(defn find-intact [{dot-claims :dot-claims}]
  (let [intact-candidates
        (reduce-kv #(if (> (count %3) 1) %1 (clojure.set/union %1 %3)) #{} dot-claims)
        
        unintact
        (reduce-kv #(if (> (count %3) 1) (clojure.set/union %1 %3) %1) #{} dot-claims)]
    (clojure.set/difference intact-candidates unintact)))
