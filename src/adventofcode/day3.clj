(ns adventofcode.day3)

(defn slurp-directions [inputfile]
  (-> inputfile
      slurp))

(def world {[0 0] 1}) ; houses with addresses / coordinates [x y] and number of presents 
(def start-state [0 0]) ; current address / coordinates

(defn x-coord [[x y]]
  x)

(defn y-coord [[x y]]
  y)

(defn north
  [state]
  [(x-coord state) (inc (y-coord state))])

(defn south
  [state]
  [(x-coord state) (dec (y-coord state))])

(defn west
  [state]
  [(dec (x-coord state)) (y-coord state)])

(defn east
  [state]
  [(inc (x-coord state)) (y-coord state)])

(defn drop-present [world address]
  (update world address inc))

(defn add-house [world address]
  (assoc world address 0))

(defn move-and-drop-present
  [[world state] direction]
  (let [new-coords (direction state)]
    (if (find world new-coords)
      [(drop-present world new-coords) new-coords]
      [(-> world
          (add-house new-coords)
          (drop-present new-coords))
       new-coords])))

(defn move-santa
  [directionstring]
  (reduce
   (fn [worldstate directionchar]
     (condp = directionchar
       \^ (move-and-drop-present worldstate north)
       \v (move-and-drop-present worldstate south)
       \> (move-and-drop-present worldstate east)
       \< (move-and-drop-present worldstate west)
       :default (throw (ex-info "Unknown direction " directionchar))))
   [world start-state] directionstring))

(defn move-and-count-houses [directionstring]
  (-> (move-santa directionstring)
      first
      keys
      count))

(defn move-robot-and-drop-present [[world santapos robopos turn] direction]
  (let [new-coords (direction robopos)]
    (if (find world new-coords)
      [(drop-present world new-coords) santapos new-coords :robo]
      [(-> world
          (add-house new-coords)
          (drop-present new-coords))
       santapos new-coords :robo])))

(defn move-santa-and-drop-present [[world santapos robopos turn] direction]
  (let [new-coords (direction santapos)]
    (if (find world new-coords)
      [(drop-present world new-coords) new-coords robopos :santa]
      [(-> world
          (add-house new-coords)
          (drop-present new-coords))
       new-coords robopos :santa])))

;; world as before plus santa position, robo position, last turn-taker
(def robo-world [{[0 0] 2} [0 0] [0 0] :robo])

(defn move-santa-and-robotsanta [directionstring]
  (reduce
   (fn [worldstate directionchar]
     (let [[world santapos robopos prevturn] worldstate]
       (condp = [prevturn directionchar]
         [:robo \^] (move-santa-and-drop-present worldstate north)
         [:robo \v] (move-santa-and-drop-present worldstate south)
         [:robo \>] (move-santa-and-drop-present worldstate east)
         [:robo \<] (move-santa-and-drop-present worldstate west)
         [:santa \^] (move-robot-and-drop-present worldstate north)
         [:santa \v] (move-robot-and-drop-present worldstate south)
         [:santa \>] (move-robot-and-drop-present worldstate east)
         [:santa \<] (move-robot-and-drop-present worldstate west)
         :default (throw (ex-info "Unknown direction or turntaker " [prevturn directionchar])))))
   robo-world directionstring))
  

(defn move-robot-and-count-houses [directionstring]
  (-> (move-santa-and-robotsanta directionstring)
      first
      keys
      count))
