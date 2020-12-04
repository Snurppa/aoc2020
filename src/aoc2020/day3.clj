(ns aoc2020.day3
  (:require [aoc2020.util :as u]))

(def SQUARE \.)
(def TREE \#)

(defn make-map [as-strings]
  (mapv #(cycle %) as-strings))

(defn char-in-position [[y x] the-map]
  (nth (get the-map y) x))

(defn check-trees [{:keys [position] :as state} the-map]
  (if (= TREE (char-in-position position the-map))
    (update state :trees inc)
    state))

(defn update-position [[y1 x1] [y2 x2]]
  [(+ y1 y2) (+ x1 x2)])

(defn end-of-the-map? [the-map [y _]]
  (>= y (count the-map)))

(defn slide-through [{:keys [position movement] :as state} the-map]
  (if (end-of-the-map? the-map position)
    state
    (slide-through 
     (-> state
         (update :position update-position movement)
         (check-trees the-map))
     the-map)))

(defn part1 []
  (let [input (u/input-as-lines "day3.txt")
        the-map (make-map input)
        result (slide-through  
                {:trees 0
                 :position [0 0] 
                 :movement [1 3]}
                the-map)]
    result))

(defn part2 [])

(comment 
  (nth (first (make-map (u/input-as-lines "day3.txt")))
       1002)
  )
