(ns xamfoo.day03.part1)

(defn overlap [{dot-claims :dot-claims}]
  (reduce-kv #(+ %1 (if (> (count %3) 1) 1 0)) 0 dot-claims))
