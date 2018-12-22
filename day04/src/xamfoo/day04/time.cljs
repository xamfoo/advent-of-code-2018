(ns xamfoo.day04.time
  (:require
    [clojure.set]
    [xamfoo.day04.util :as util]))

(defn parse [s]
  (let [[year month day hour minute]
        (map util/parse-int (rest (re-find #"(\S+)-(\S+)-(\S+)\s*(\S+):(\S+)" s)))]
    {::year year ::month month ::day day ::hour hour ::minute minute}))

(defn value-of [t]
  ((juxt ::year ::month ::day ::hour ::minute) t))
