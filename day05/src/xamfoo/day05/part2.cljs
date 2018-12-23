(ns xamfoo.day05.part2
  (:require
    [xamfoo.day05.util :as util]))

(defn shortest-len [[polymer]]
  (let [types (util/polymer-types polymer)

        variants
        (reduce
          (fn [acc t] (assoc acc t (util/reduce-polymer (util/rm-type polymer t))))
          {}
          types)]
    (count (val (apply min-key #(count (val %)) variants)))))
