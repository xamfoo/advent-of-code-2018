(ns xamfoo.day04.part2)

(defn guard-sleepiest-minute [guard-data]
  (reduce-kv
    (fn [acc guard minutes]
      (if (> (count minutes) 0)
        (assoc acc guard (apply max-key val minutes))
        acc))
    {}
    guard-data))

(defn id-x-min [guard-data]
  (let [sleepiest-minutes (guard-sleepiest-minute guard-data)
        [guard-sleep-most-same-min [minute]] (apply max-key #(val (val %)) sleepiest-minutes)]
    (* guard-sleep-most-same-min minute)))
