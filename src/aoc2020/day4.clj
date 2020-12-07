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

(def checks
 "byr (Birth Year) - four digits; at least 1920 and at most 2002.
iyr (Issue Year) - four digits; at least 2010 and at most 2020.
eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
hgt (Height) - a number followed by either cm or in:
If cm, the number must be at least 150 and at most 193.
If in, the number must be at least 59 and at most 76.
hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
pid (Passport ID) - a nine-digit number, including leading zeroes.
cid (Country ID) - ignored, missing or not." 
  {"byr" (fn [v] (<= 1920 (Integer/parseInt v) 2002))
   "iyr" (fn [v] (<= 2010 (Integer/parseInt v) 2020))
   "eyr" (fn [v] (<= 2020 (Integer/parseInt v) 2030))
   "hcl" (fn [v] (re-matches #"#[a-z0-9]{6}" v))
   "ecl" (fn [v] (contains? #{"amb" "blu" "brn" "gry" "grn" "hzl" "oth"} v))
   "pid" (fn [v] (re-matches #"\d{9}" v))
   "cid" (constantly true)
   "hgt" (fn [v] 
           (if-let [[_ n sys] (re-matches #"(\d+)(cm|in)" v)]
             (case sys
               "cm" (<= 150 (Integer/parseInt n) 193)
               "in" (<= 59 (Integer/parseInt n) 76))
             false))})

(defn invalid-fields [passport]
  (for [[k v] passport
        :let [checker (get checks k)]
        :when (not (checker v))]
    k))

(defn valid-passport-p2? [passport]
  (and 
   (valid-passport? passport)
   (empty? (invalid-fields passport))))


(defn part1 []
  (let [inputs (u/passport-day4-input "day4.txt")]
    (->> inputs 
         (map ->passport)
         (filter valid-passport?)
         (count))))

(defn part2 []
  (let [inputs (u/passport-day4-input "day4.txt")]
    (->> inputs
         (map ->passport)
         (filter valid-passport-p2?)
         (count))))
