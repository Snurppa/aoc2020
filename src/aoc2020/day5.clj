(ns aoc2020.day5
  (:require [aoc2020.util :as u]))

(def bit->func
  {\F first \B second
   \L first \R second})

(defn reduce-funcs [the-range funcs]
  (reduce 
   (fn [acc-range func]
     (let [item-count (count acc-range)
           splitted (split-at (/ item-count 2) acc-range)]
       (func splitted)))
   the-range
   funcs))

(defn get-position [range bits]
  (first (reduce-funcs range (map bit->func bits))))

(defn ->boarding-pass [pass-string]
  (let [row-str (subs pass-string 0 7)
        row (get-position (range 128) row-str)
        seat-str (subs pass-string 7)
        seat (get-position (range 8) seat-str)]
    {:row-raw row-str
     :seat-raw seat-str
     :row row
     :seat seat
     :sid (+ (* row 8) seat)}))

(defn part1 []
  (let [passes (u/input-as-lines "day5.txt")]
    (->> passes
         (map ->boarding-pass)
         (map :sid)
         (apply max))))

(defn part2 [])

