(ns aoc2020.day4
  (:require [aoc2020.util :as u]
            [clojure.string :as s]
            [clojure.set :as set]))

(defn ->passport [passport-line]
  (->> (s/split passport-line #" ")
       (map #(s/split % #":"))
       (into {})))

(def required-keys 
  ;  "cid"
  #{"eyr" "hcl" "hgt" "pid" "ecl" "byr" "iyr"})

(defn valid-passport? [passport]
  (empty? (set/difference required-keys (set (keys passport)))))

(defn part1 []
  (let [inputs (u/passport-day4-input "day4.txt")]
    (->> inputs 
         (map ->passport)
         (filter valid-passport?)
         (count))))
(defn part2 [])
