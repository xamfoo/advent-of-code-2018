(ns xamfoo.day02.part1)

(defn partial-checksum [id]
  (let [freqs (set (vals (frequencies id)))]
    [(if (contains? freqs 2) 1 0)
     (if (contains? freqs 3) 1 0)]))

(defn checksum [input]
  (apply
    *
    (apply
      map + (map partial-checksum input))))
