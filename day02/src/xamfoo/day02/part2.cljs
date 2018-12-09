(ns xamfoo.day02.part2
  (:require
    [clojure.set]
    ["binary-search" :as bin-search]))

(defn one-char-diff [id1 {:keys [starts-with ends-with]}]
  (reduce
    (fn [acc i]
      (let [front-str (subs id1 0 i)
            back-str (subs id1 (+ i 1))
            front-set (get starts-with front-str)
            back-set (get ends-with back-str)
            result (disj (clojure.set/intersection front-set back-set) id1)]
        (when (< 0 (count result))
          (reduced {:position i :matches result}))))
    nil
    (range (count id1))))

(defn split-word [string]
  (let [l (count string)
        r (range l)]
    {:starts-with (map #(subs string 0 %) r)
     :ends-width (map #(subs string (- l %)) r)}))

(defn merge-lookup [lookup values id]
  (into
    lookup
    (map
      (fn [v] [v (conj (get lookup v #{}) id)])
      values)))

(defn create-lookup [input]
  (reduce
    (fn [acc id]
      (let [r1 (split-word id)
            sw (:starts-with r1)
            ew (:ends-width r1)]
        {:starts-with (merge-lookup (:starts-with acc) sw id)
         :ends-with (merge-lookup (:ends-with acc) ew id)}))
    {:starts-with {} :ends-with {}}
    input))

(defn common-letters-of-match [input]
  (let [lookup (create-lookup input)]
    (reduce
      (fn [acc id1]
        (if-let [{:keys [position]} (one-char-diff id1 lookup)]
          (reduced (str (subs id1 0 position) (subs id1 (+ position 1))))
          nil))
      nil
      input)))
