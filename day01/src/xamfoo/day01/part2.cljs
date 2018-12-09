(ns xamfoo.day01.part2)

(defn find-freq [input]
  (loop [all-freq #{0} freq-changes input freq 0]
    (if (empty? freq-changes)
      (recur all-freq input freq)
      (let [new-freq (+ freq (first freq-changes))]
        (if (contains? all-freq new-freq)
          new-freq
          (recur (conj all-freq new-freq) (rest freq-changes) new-freq))))))
