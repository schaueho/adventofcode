(ns adventofcode.2015.day3)

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
       (throw (ex-info "Unknown direction " directionchar))))
   [world start-state] directionstring))

(defn move-and-count-houses [directionstring]
  (-> (move-santa directionstring)
      first
      keys
      count))

;;; part 2

(defn find-new-coords [[world santapos robopos prevturn] direction]
  (condp = prevturn
    :robo (direction santapos)
    :santa (direction robopos)
    (throw (ex-info "Unknown turntaker" prevturn))))

(defn new-world [newworld oldsanta oldrobo prevturn new-coords]
  (condp = prevturn
    :robo [newworld new-coords oldrobo :santa]
    :santa [newworld oldsanta new-coords :robo] 
    (throw (ex-info "Unknown turntaker" prevturn))))

(defn move-santa-or-robot [worldstate direction]
  (let [[world oldsanta oldrobot prevturn] worldstate
        new-coords (find-new-coords worldstate direction)]
    (if (find world new-coords)
      (-> world
          (drop-present new-coords)
          (new-world oldsanta oldrobot prevturn new-coords))
      (-> world
          (add-house new-coords)
          (drop-present new-coords)
          (new-world oldsanta oldrobot prevturn new-coords)))))

;; world as before plus santa position, robo position, last turn-taker
(def robo-world [{[0 0] 2} [0 0] [0 0] :robo])

(defn move-santa-and-robotsanta [directionstring]
  (reduce
   (fn [worldstate directionchar]
     (let [[world santapos robopos prevturn] worldstate]
       (condp = directionchar
         \^ (move-santa-or-robot worldstate north)
         \v (move-santa-or-robot worldstate south)
         \> (move-santa-or-robot worldstate east)
         \< (move-santa-or-robot worldstate west)
         (throw (ex-info "Unknown direction or turntaker " [prevturn directionchar])))))
   robo-world directionstring))

(defn move-robot-and-count-houses [directionstring]
  (-> (move-santa-and-robotsanta directionstring)
      first
      keys
      count))
