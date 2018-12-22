(ns xamfoo.day04.part1)

(defn sum-guard-minutes [guard-data]
  (reduce-kv
    (fn [acc guard minutes]
      (assoc acc guard (apply + (vals minutes))))
    {}
    guard-data))

(defn id-x-min [guard-data]
  (let [guard-data-sum (sum-guard-minutes guard-data)
        sleepiest-guard (key (apply max-key val guard-data-sum))
        sleepiest-minute (key (apply max-key val (get guard-data sleepiest-guard)))]
    (* sleepiest-guard sleepiest-minute)))
