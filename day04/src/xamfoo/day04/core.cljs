(ns xamfoo.day04.core
  (:require
    [xamfoo.day04.event :as event]
    [xamfoo.day04.event.type :as event.type]
    [xamfoo.day04.guard :as guard]
    [xamfoo.day04.time :as time]))

(defn parse-input [input]
  (let [events (map event/parse input)
        sorted-events (sort-by event/value-of events)]
    (:result
      (reduce
        (fn [{:keys [result guard]} {{new-guard ::guard/id :or {new-guard guard}} ::event/desc :as event}]
          {:result (conj result (assoc-in event [::event/desc ::guard/id] new-guard)) :guard new-guard})
        {:result [] :guard nil}
        sorted-events))))

(defn split-events [event-list]
  (:result
    (reduce
      (fn [{:keys [result prev-guard]} {{guard-id ::guard/id} ::event/desc :as event}]
        (cond
          (= prev-guard guard-id)
          {:result (conj (pop result) (conj (peek result) event))
           :prev-guard guard-id}

          :else
          {:result (conj result [event]) :prev-guard guard-id}))
      {:result [] :prev-guard nil}
      event-list)))

(defn each-day [event-list]
  (dissoc
    (reduce
      (fn [{:keys [minutes last-sleep guard] :as acc}
           {t ::event/time
            {event-type ::event/type guard-id ::guard/id} ::event/desc
            :as event}]
        (cond
          (= event-type ::event.type/guard)
          (assoc acc :guard guard-id)

          (and (= last-sleep nil) (= event-type ::event.type/sleep))
          (assoc acc :last-sleep t)
          
          (and last-sleep (= event-type ::event.type/wake))
          (assoc
            acc
            :minutes
            (reduce
              (fn [acc m] (assoc acc m (+ (get acc m 0) 1)))
              minutes
              (range (::time/minute last-sleep) (::time/minute t)))
            :last-sleep nil)))
      {:minutes {} :last-sleep nil :guard nil}
      event-list)
    :last-sleep))

(defn day-to-guard-minutes [days]
  (reduce
    (fn [acc {:keys [minutes guard]}]
      (assoc
        acc
        guard
        (reduce-kv
          (fn [new-mins k v]
            (assoc new-mins k (+ (get new-mins k 0) v)))
          (get acc guard {})
          minutes)))
    {}
    days))

(defn make-data [input]
  (-> input
      parse-input
      split-events
      (#(map each-day %))
      day-to-guard-minutes))
