(ns xamfoo.day03.fabric)

(defn parse-line [s]
  (rest (re-find #"#(\S+)\s*@\s*(\S+),(\S+):\s*(\S+)x(\S+)" s)))

(defn parse-int [x] (js/parseInt x 10))

(defn parse-input [input]
  (map
    #(let [[id i-left i-top i-width i-height] (parse-line %)

           [left top width height]
           (map parse-int [i-left i-top i-width i-height])
           
           right (+ left width -1)
           bottom (+ top height -1)]
       {:id id
        :left left
        :top top
        :width width
        :height height
        :right right
        :bottom bottom})
    input))

(defn update-fabric-claim [fabric {:keys [top left right bottom id] :as claim}]
  (assoc
    fabric
    :dot-claims
    (reduce
      #(assoc %1 %2 (conj (get %1 %2 #{}) id))
      (:dot-claims fabric)
      (for [x (range left (+ 1 right)) y (range top (+ 1 bottom))] [x y]))))

(defn update-fabric [claims]
  (reduce update-fabric-claim {:dot-claims {}} claims))

(defn make [input]
  (let [claims (parse-input input)]
    (update-fabric claims)))
