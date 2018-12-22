(ns xamfoo.day04.event
  (:require
    [xamfoo.day04.event.type :as event-type]
    [xamfoo.day04.guard :as guard]
    [xamfoo.day04.time :as time]
    [xamfoo.day04.util :as util]))

(defn parse-desc [s]
  (cond
    (re-find #"falls asleep" s) {::type ::event-type/sleep}
    (re-find #"wakes up" s) {::type ::event-type/wake}

    (re-find #"Guard #" s)
    (let [[id] (map util/parse-int (rest (re-find #"Guard #(\S+)" s)))]
      {::type ::event-type/guard ::guard/id id})

    :else {}))

(defn parse [s]
  (let [[time-s desc-s] (rest (re-find #"\[(.*)\]\s*(\S.*)" s))
        t (time/parse time-s)
        desc (parse-desc desc-s)]
    {::time t ::desc desc}))

(defn value-of [{t ::time}]
  (time/value-of t))
