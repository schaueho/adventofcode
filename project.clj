(defproject adventofcode "0.1.0-SNAPSHOT"
  :description "Solutions to adventofcode.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [pandect "0.5.4"]
                 [com.rpl/specter "0.8.0"]]
  :profiles {:dev {:dependencies [[midje "1.8.2"]
                                  [org.clojure/tools.nrepl "0.2.11"]
                                  [org.clojure/tools.trace "0.7.9"]
                                  [refactor-nrepl "1.1.0"]
                                  [cider/cider-nrepl "0.9.1"]]}})
