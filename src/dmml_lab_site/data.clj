(ns dmml-lab-site.data
  (:require [schema.core :as sc]
            [schema.macros :as sm]
            [clojure.string :as str]))

(sm/defn last-name :- sc/Str
  [name :- sc/Str]
  (-> name (str/split #"\s+") last))

;; TODO: data that can later be put in external files.
(def faculty (sort-by (comp last-name :name)
                      [{:homepage "http://cs.gmu.edu/~dbarbara/"
                        :name "Daniel Barbara"
                        :interests nil
                        :img "grey_square.jpg"}
                       {:homepage "http://cs.gmu.edu/~carlotta/students/"
                        :name "Carlotta Domeniconi"
                        :interests nil
                        :img "grey_square.jpg"}
                       {:homepage "http://www.cs.gmu.edu/~jessica/"
                        :name "Jessica Lin"
                        :interests nil
                        :img "grey_square.jpg"}
                       {:homepage "http://www.cs.gmu.edu/~hrangwal/"
                        :name "Huzefa Rangwala"
                        :interests nil
                        :img "grey_square.jpg"}]))

(def phd-students (sort-by (comp last-name :name)
                           [{:homepage "http://dynamictyping.com"
                             :name "Matt Revelle"
                             :interests ["probabilistic models" "unsupervised learning" "social networks"]
                             :img "matt_revelle.jpg"}
                            {:homepage "#"
                             :name "David Etter"
                             :interests nil
                             :img "grey_square.jpg"}
                            {:homepage "#"
                             :name "Rohan Khade"
                             :interests nil
                             :img "grey_square.jpg"}
                            {:homepage "http://www.cs.gmu.edu/~tsaha/"
                             :name "Tanwistha Saha"
                             :interests ["multi-label learning" "active learning"
                                         "semi-supervised learning" "social networks"]
                             :img "tanwistha_saha.jpg"}
                            {:homepage "http://mason.gmu.edu/~rotunba/"
                             :name "Rasaq Otunba"
                             :interests nil
                             :img "grey_square.jpg"}
                            {:homepage "#"
                             :name "Xing Wang"
                             :interests nil
                             :img "grey_square.jpg"}
                            {:homepage "#"
                             :name "Anveshi Charuvaka"
                             :interests nil
                             :img "grey_square.jpg"}
                            {:homepage "https://sites.google.com/site/azadnaik/"
                             :name "Azad Naik"
                             :interests ["multi-task learning" "hierarchical classification"]
                             :img "azad_naik.jpg"}
                            {:homepage "#"
                             :name "Debdipto Misra"
                             :interests nil
                             :img "grey_square.jpg"}]))

(def ms-students (sort-by (comp last-name :name)
                          [{:homepage "#"
                            :name "Nikhil Muralidhar"
                            :email ""
                            :img "grey_square.jpg"}]))
(def ug-students (sort-by (comp last-name :name)
                          [{:homepage "#"
                            :name "Mackenzie Sweeney"
                            :email ""
                            :img "grey_square.jpg"}]))

(def visiting-scholars (sort-by (comp last-name :name)
                                []))

(def alumni (sort-by :year >
                     [{:homepage "#"
                       :name "Sam Blasiak"
                       :year 2013
                       :previous "PhD"
                       :img "grey_square.jpg"}
                      {:homepage "#"
                       :name "Yazhou Ren"
                       :year 2014
                       :previous "Visiting Scholar"
                       :img "grey_square.jpg"}
                      {:homepage "http://cs.gmu.edu/~pwang7/"
                       :name "Pu Wang"
                       :year 2011
                       :previous "PhD"
                       :img "grey_square.jpg"}
                      {:homepage "#"
                       :name "Loulwah AlSumait"
                       :year 2009
                       :previous "PhD"
                       :img "grey_square.jpg"}
                      {:homepage "http://mason.gmu.edu/~zrasheed/"
                       :name "Zeehasham Rasheed"
                       :year 2013
                       :previous "PhD"
                       :img "grey_square.jpg"}
                      {:homepage "#"
                       :name "James Rogers"
                       :year 2010
                       :previous "PhD"
                       :img "grey_square.jpg"}]))

(def projects {})
(def publications {})
(def datasets {})
(def courses (sort-by :id
                      [{:id "CS 484"
                        :title "Data Mining"
                        :url "http://catalog.gmu.edu/preview_course_nopop.php?catoid=25&coid=252901"}
                       {:id "CS 659"
                        :title "Theory and Applications of Data Mining"
                        :url "http://catalog.gmu.edu/preview_course_nopop.php?catoid=25&coid=258170"}
                       {:id "CS 680"
                        :title "Natural Language Processing"
                        :url "http://catalog.gmu.edu/preview_course_nopop.php?catoid=25&coid=252926"}
                       {:id "CS 688"
                        :title "Pattern Recognition"
                        :url "http://catalog.gmu.edu/preview_course_nopop.php?catoid=25&coid=252934"}
                       {:id "CS 775"
                        :title "Advanced Pattern Recognition"
                        :url "http://catalog.gmu.edu/preview_course_nopop.php?catoid=25&coid=252948"}
                       {:id "CS 780"
                        :title "Data Mining on Multimedia Data"
                        :url "http://catalog.gmu.edu/preview_course_nopop.php?catoid=25&coid=252952"}]))
