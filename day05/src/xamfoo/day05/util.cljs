(ns xamfoo.day05.util
  (:require
    [clojure.string :as cljstr]
    [clojure.set :as cljset]))

(defn reactable [a b]
  (and (= (cljstr/upper-case a) (cljstr/upper-case b))
       (not= a b)))

(defn reduce-polymer [raw-polymer]
  (loop [start []
         end (apply list raw-polymer)]
    (cond
      (= (count end) 0) (cljstr/join start)

      (and (> (count start) 0) (reactable (peek start) (peek end)))
      (recur (pop start) (pop end))

      :else (recur (conj start (peek end)) (pop end)))))

(defn polymer-types [raw-polymer]
  (let [types (set raw-polymer)
        upper-types (set (map cljstr/upper-case types))
        result (set (map (fn [x] #{x (cljstr/lower-case x)}) upper-types))]
    result))

(defn rm-type [raw-polymer t]
  (cljstr/join
    (reduce
      (fn [acc x] (if (t x) acc (conj acc x)))
      []
      raw-polymer)))
