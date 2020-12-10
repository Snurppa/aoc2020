(ns aoc2020.day6
  (:require [aoc2020.util :as u]
            [clojure.set :as set]))


(defn anyone-group-answers [group]
  (set (reduce str group)))

(defn everyone-group-answers [group]
  (reduce set/intersection (map set group)))

(defn part1 []
  (let [answers (u/day6-input)]
    (->> (map anyone-group-answers answers)
         (map count)
         (reduce + 0))))

(defn part2 []
  (let [answers (u/day6-input)]
    (->> (map everyone-group-answers answers)
         (map count)
         (reduce + 0))))
