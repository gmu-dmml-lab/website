(ns dmml-lab-site.data
  (:require [schema.core :as sc]
            [schema.macros :as sm]
            [clojure.string :as str]))

(sm/defn last-name :- sc/Str
  [name :- sc/Str]
  (-> name (str/split #"\s+") last))

(sm/defn add-interests? :- {sc/Any sc/Any}
  [profile :- {sc/Any sc/Any}]
  (assoc profile :interests? (not (empty? (:interests profile)))))

;; TODO: data that can later be put in external files.
(def faculty (sort-by (comp last-name :name)
                      (->>
                       [{:homepage "http://cs.gmu.edu/~dbarbara/"
                         :name "Daniel Barbará"
                         :interests ["anomaly detection" "bioinformatics" "clustering"]
                         :img "daniel_barbara.jpg"}
                        {:homepage "http://cs.gmu.edu/~carlotta/students/"
                         :name "Carlotta Domeniconi"
                         :interests ["pattern recognition" "classification" "clustering" "feature relevance estimation" "text mining" "bioinformatics"]
                         :img "carlotta_domeniconi.jpg"}
                        {:homepage "http://www.cs.gmu.edu/~jessica/"
                         :name "Jessica Lin"
                         :interests ["time series and spatiotemporal pattern discovery" "visualization" "anomaly detection"]
                         :img "jessica_lin.jpg"}
                        {:homepage "http://www.cs.gmu.edu/~hrangwal/"
                         :name "Huzefa Rangwala"
                         :interests ["structural bioinformatics" "chemoinformatics" "genomics" "high performance computing"]
                         :img "huzefa_rangwala.jpg"}]
                       (map add-interests?))))

(def phd-students (sort-by (comp last-name :name)
                           (->>
                            [{:homepage "http://dynamictyping.com"
                              :name "Matt Revelle"
                              :interests ["probabilistic models" "unsupervised learning" "dynamic networks" "social networks"]
                              :img "matt_revelle.jpg"}
                             {:homepage "#"
                              :name "David Etter"
                              :interests nil
                              :img "grey_square.jpg"}
                             {:homepage "http://mason.gmu.edu/~rkhade/"
                              :name "Rohan Khade"
                              :interests ["association rule mining" "contrast set mining" "time series data" "mixed data"]
                              :img "rohan_khade.jpg"}
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
                             {:homepage "http://mason.gmu.edu/~dmisra2/"
                              :name "Debdipto Misra"
                              :interests ["computer vision" "spatio-temporal signature detection" "biomedical image processing" "prosthetic control"]
                              :img "deb_misra.jpg"}]
                            (map add-interests?))))

(def ms-students (sort-by (comp last-name :name)
                          (->>
                           [{:homepage "#"
                             :name "Nikhil Muralidhar"
                             :email ""
                             :img "grey_square.jpg"}]
                           (map add-interests?))))
(def ug-students (sort-by (comp last-name :name)
                          (->>
                           [{:homepage "#"
                             :name "Mackenzie Sweeney"
                             :email ""
                             :img "grey_square.jpg"}]
                           (map add-interests?))))

(def visiting-scholars (sort-by (comp last-name :name)
                                (->>
                                 []
                                 (map add-interests?))))

(def alumni (sort-by :year >
                     (->>
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
                        :img "grey_square.jpg"}]
                      (map add-interests?))))

(def projects {})

(def publications [{:title "Efficient Clustering of Metagenomic Sequences using Locality Sensitive Hashing"
                    :authors ["Zeehasham Rasheed" "Huzefa Rangwala" "Daniel Barbará"]
                    :venue "SIAM International Conference on Data Mining (SDM)"
                    :year 2012
                    :link nil}
                   {:title "Spectrum Based Fraud Detection in Social Networks"
                    :authors ["Xiawei Ying" "Xintao Wu" "Daniel Barbará"]
                    :venue "International Conference on Data Engineering (ICDE)"
                    :year 2011
                    :link nil}
                   {:title "Joint Segmentation and Clustering in Text Corpuses"
                    :authors ["Sam Blasiak" "Sithu Sudarshan" "Huzefa Rangwala"]
                    :venue "SIAM International Conference on Data Mining (SDM)"
                    :year 2013
                    :link "http://www.cs.gmu.edu/~hrangwal/node/439"}
                   {:title "Sparsification and Sampling of Networks for Collective Classification"
                    :authors ["Tanwistha Saha" "Huzefa Rangwala" "Carlotta Domeniconi"]
                    :venue "International Conference on Social Computing, Behavioral-Cultural Modeling, & Prediction (SBP)"
                    :year 2013
                    :link "http://www.cs.gmu.edu/~hrangwal/node/440"}
                   {:title "Multi-task Learning for Classifying Proteins with Dual Hierarchies"
                    :authors ["Anveshi Charuvaka" "Huzefa Rangwala"]
                    :venue "IEEE International Conference on Data Mining (ICDM)"
                    :year 2012
                    :link "http://www.cs.gmu.edu/~hrangwal/node/420"}
                   {:title "Transductive Multi-label Ensemble Classification for Protein Function Prediction"
                    :authors ["Guoxian Yu" "Carlotta Domeniconi" "Huzefa Rangwala" "Guoji Zhang" "Zhiwen Yu"]
                    :venue "ACM SIGKDD International Conference on Knowledge Discovery and Data Mining (KDD)"
                    :year 2012
                    :link "http://www.cs.gmu.edu/~hrangwal/node/427"}
                   {:title "Co-Participation Networks Using Comment Information"
                    :authors ["Huzefa Rangwala" "Salman Jamali"]
                    :venue "AAAI International Conference on Weblogs and Social Media (ICWSM)"
                    :year 2010
                    :link "http://www.cs.gmu.edu/~hrangwal/node/339"}
                   {:title "Digging Digg: Comment Mining, Popularity Prediction, and Social Network Analysis"
                    :authors ["Salman Jamali" "Huzefa Rangwala"]
                    :venue "IEEE International Conference on Web Information Systems and Mining (WISM)"
                    :year 2009
                    :link "http://www.cs.gmu.edu/~hrangwal/node/333"}
                   {:title "Boosted Mean Shift Clustering"
                    :authors ["Yazhou Ren" "Uday Kamath" "Carlotta Domeniconi" "Guoji Zhang"]
                    :venue "European Conference on Machine Learning and Principles and Practice of Knowledge Discovery in Databases (ECML/PKDD)"
                    :year 2014
                    :link "http://cs.gmu.edu/~carlotta/publications/BMSC.pdf"}
                   {:title "A Weighted Adaptive Mean Shift Clustering Algorithm"
                    :authors ["Yazhou Ren" "Carlotta Domeniconi" "Guoji Zhang" "Guoxian Yu"]
                    :venue "SIAM International Conference on Data Mining (SDM)"
                    :year 2014
                    :link "http://cs.gmu.edu/~carlotta/publications/SDM14.pdf"}
                   {:title "FLIP: Active Learning for Relational Network Classification"
                    :authors ["Tanwistha Saha" "Huzefa Rangwala" "Carlotta Domeniconi"]
                    :venue "European Conference on Machine Learning and Principles and Practice of Knowledge Discovery in Databases (ECML/PKDD)"
                    :year 2014
                    :link "http://www.cs.gmu.edu/~tsaha/Homepage/Publications_files/ECML14_FLIP_CR.pdf"}
                   {:title "Weighted-Object Ensemble Clustering"
                    :authors ["Yazhou Ren" "Carlotta Domeniconi" "Guoji Zhang" "Guoxian Yu"]
                    :venue "IEEE International Conference on Data Mining (ICDM)"
                    :year 2013
                    :link "http://cs.gmu.edu/~carlotta/publications/ICDM13.pdf"}
                   {:title "Transductive Multi-label Ensemble Classification for Protein Function Prediction"
                    :authors ["Yazhou Ren" "Carlotta Domeniconi" "Huzefa Rangwala" "Guoji Zhang" "Guoxian Yu"]
                    :venue "ACM SIGKDD International Conference on Knowledge Discovery and Data Mining (KDD)"
                    :year 2012
                    :link "http://cs.gmu.edu/~carlotta/publications/KDD12.pdf"}
                   {:title "Nonparametric Bayesian Co-clustering Ensembles"
                    :authors ["Pu Wang" "Kathryn B. Laskey" "Carlotta Domeniconi" "Michael I. Jordan"]
                    :venue "SIAM International Conference on Data Mining (SDM)"
                    :year 2011
                    :link "http://cs.gmu.edu/~carlotta/publications/NBCCE.pdf"}
                   {:title "Visualizing Variable-length Time Series Motifs"
                    :authors ["Yuan Li" "Jessica Lin" "Tim Oates"]
                    :venue "SIAM International Conference on Data Mining (SDM)"
                    :year 2012
                    :link "http://www.cs.gmu.edu/~jessica/publications/grammar_motif_sdm12.pdf"}])

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
