-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quizsystem
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `fragenkatalog`
--

DROP TABLE IF EXISTS `fragenkatalog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fragenkatalog` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(1000) DEFAULT NULL,
  `correct_answer` varchar(1000) DEFAULT NULL,
  `incorrect_answer_1` varchar(1000) DEFAULT NULL,
  `incorrect_answer_2` varchar(1000) DEFAULT NULL,
  `incorrect_answer_3` varchar(1000) DEFAULT NULL,
  `difficulty` int DEFAULT NULL,
  `module` text,
  `explanation` varchar(1000) DEFAULT NULL,
  `status` varchar(45) DEFAULT 'ungeprüft',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fragenkatalog`
--

LOCK TABLES `fragenkatalog` WRITE;
/*!40000 ALTER TABLE `fragenkatalog` DISABLE KEYS */;
INSERT INTO `fragenkatalog` VALUES (1,'Wenn das Wetter gut ist, wird der Bauer bestimmt den Eber, das Ferkel und …','die Sau rauslassen','auf die Kacke hauen','die Nacht durchzechen','einen draufmachen',1,'testing','Beispielerklärung','geprüft'),(2,'Wessen Genesung schnell voranschreitet, der erholt sich …','zusehends','glotzends','anschauends','hinguckends',1,'testing','Beispielerklärung','geprüft'),(3,'Wobei gibt es keine geregelten Öffnungszeiten?','Fensterläden','Baumärkte','Teppichgeschäfte','Möbelhäuser',2,'testing','Beispielerklärung','geprüft'),(4,'Wobei handelt es sich um ein Notsignal im internationalen Funkverkehr?','Mayday','Down Town','Jetset','Flower Power',2,'testing','Beispielerklärung','geprüft'),(5,'Wie nennt man von Gletschern transportierten Gesteinsschutt?','Moräne','Muräne','Morelle','Murnau',3,'testing','Beispielerklärung','geprüft'),(6,'Was ist ein Betriebssystem?','Eine Art von Computer-Programm, das die Hardware des Computers verwaltet.','Ein Programm, das die Daten auf einem Computer speichert.','Ein Programm, das die Geschwindigkeit des Computers erhöht.','Ein Programm, das den Computer vor Viren schützt.',2,'Software-Engineering','Beispielerklärung','geprüft'),(7,'Eine Bitfolge besteht aus ... ','einer Abfolge der Symbole 0 und 1.','einer Abfolge der Symbole A und B.','einer Abfolge der Symbole 1 und 2.','einer Abfolge der Symbole 0 und 2',1,'Software-Engineering','Beispielerklärung','geprüft'),(8,'Mit dem Begriff Softwaretechnik ...','wird sowohl die Erstellung als auch die Anwendung von umfangreichen Softwaresystemen bezeichnet.','wird eine 145 Jahre alte Ingenieursdisziplin bezeichnet.','wird eine leichte Akrobatik-Fertigkeit bezeichnet.','wird ein Computermodell beschrieben.',1,'Software-Engineering','Beispielerklärung','geprüft'),(9,'Welche der nachfolgenden Aussagen zu Zielkonflikten im Software Engineering ist richtig? ','Der Fokus auf eine bestimmte Zielgröße hat Auswirkungen auf die Erreichung der anderen Zielgrößen.','Wenn nach Beginn eines Projekts alle Anforderungen an das System bekannt sind, ändern sich die Zielgrößen nicht mehr.','Kundenzufriedenheit ist ein wichtiges Ziel, jedoch im Vergleich zu den Zielen Kosten, Termin und Qualität nicht das wichtigste Ziel.','Ein Software-Projekt ist nur dann erfolgreich, wenn es keine Zielkonflikte besitzt',3,'Software-Engineering','Beispielerklärung','geprüft'),(10,'Wie lautet die Reihenfolge der Phasen im Softwarelebenszyklus?','Planung, Entwicklung, Betrieb, Wartung und Abschaltung','Entwicklung, Planung, Wartung, Betrieb und Abschaltung','Planung, Betrieb, Entwicklung, Wartung und Abschaltung','Planung, Entwicklung, Wartung, Betrieb und Abschaltung',2,'Software-Engineering','Beispielerklärung','geprüft'),(11,'Welcher der folgenden Modelle wird häufig zur Softwareentwicklung verwendet, um den Entwicklungsprozess in mehrere Phasen zu unterteilen und die Rückkopplung zwischen diesen Phasen zu ermöglichen?','Wasserfallmodell','Chaos-Modell','Monolithisches Modell','Spaghettimodell',1,'Software-Engineering','Beispielerklärung','geprüft'),(12,'Welches der folgenden Konzepte bezieht sich darauf, dass eine Klasse nicht direkt von einer spezifischen Klasse, sondern von einer abstrakten Klasse oder einem Interface erbt, um Flexibilität und Wartbarkeit in Softwareprojekten zu fördern?','Abstraktion','Vererbung','Polymorphismus','Kapselung',2,'Software-Engineering','Beispielerklärung','geprüft'),(13,'Welches Prinzip in der Softwareentwicklung besagt, dass eine Klasse sollte nur einen Grund haben, sich zu ändern, und dass eine Klasse nur eine Verantwortung übernehmen sollte?','Single Responsibility Principle (SRP)','Open/Closed Principle (OCP)','Liskov Substitution Principle (LSP)','Dependency Inversion Principle (DIP)',3,'Software-Engineering','Beispielerklärung','geprüft'),(14,'Welche der folgenden Testarten ist speziell darauf ausgerichtet, sicherzustellen, dass eine Softwareeinheit nach Modifikationen immer noch gemäß den Anforderungen funktioniert?','Regressionstest','Unittest','Integrationstest','Systemtest',3,'Software-Engineering','Beispielerklärung','geprüft'),(15,'Welche agile Entwicklungsmethode betont die enge Zusammenarbeit zwischen Kunden, Entwicklern und Tester während des gesamten Softwareentwicklungsprozesses?','Scrum','Kanban','Extreme Programming','Feature-Driven Development',1,'Software-Engineering','Beispielerklärung','geprüft'),(16,'Welcher Begriff beschreibt die Praxis, einen Entwicklungsprozess in kleinere, wiederholbare und inkrementelle Teile aufzuteilen, um flexibler auf Änderungen reagieren zu können?','Iterative Entwicklung','Refactoring','Continuous Integration','Code Review',2,'Software-Engineering','Beispielerklärung','geprüft'),(17,'Welches Designprinzip besagt, dass Softwarekomponenten so gestaltet sein sollten, dass sie für Erweiterungen offen, aber für Modifikationen geschlossen sind?','Open/Closed Principle','Liskov Substitution Principle','Single Responsibility Principle','Dependency Inversion Principle',3,'Software-Engineering','Beispielerklärung','geprüft'),(18,'Welches Softwareentwicklungsmodell betont die iterative Entwicklung, enge Zusammenarbeit mit Kunden und schnelle Anpassung an sich ändernde Anforderungen?','Wasserfallmodell','Agile Modell','V-Modell','Spiralmodell',3,'Software-Engineering','Beispielerklärung','geprüft'),(19,'Welches Muster in der Softwareentwicklung ermöglicht es, eine Schnittstelle für eine Gruppe von Algorithmen zu definieren und diese Algorithmen austauschbar zu machen, ohne die Klienten, die sie verwenden, zu ändern?','Strategiemuster','Beobachtermuster','Fassadenmuster','Dekorierermuster',1,'Software-Engineering','Beispielerklärung','geprüft'),(20,'Welches Testing-Konzept bezieht sich darauf, dass ein Programm oder eine Funktion bei bestimmten Eingaben immer das gleiche erwartete Ergebnis liefert?','Deterministisches Testen','Black-Box-Testing','White-Box-Testing','Funktionstests',1,'Software-Engineering','Beispielerklärung','geprüft'),(21,'Welcher Begriff beschreibt den Prozess der Umwandlung von Programmcode in eine für die Ausführung geeignete Form?','Kompilierung','Interpretation','Linking','Assemblierung',1,'Software-Engineering','Beispielerklärung','geprüft'),(22,'Welche Datenstruktur wird verwendet, um Elemente in einem Last-In-First-Out (LIFO)-Prinzip zu verwalten?','Stack','Queue','List','Tree',1,'Software-Engineering','Beispielerklärung','geprüft'),(23,'Welches Konzept in der objektorientierten Programmierung bezieht sich darauf, dass eine Klasse mehrere Methoden mit dem gleichen Namen haben kann, aber unterschiedliche Parameter?','Overloading','Overriding','Inheritance','Abstraction',2,'Software-Engineering','Beispielerklärung','geprüft'),(24,'Welche Phase im Softwareentwicklungslebenszyklus beinhaltet das Identifizieren, Entwerfen und Spezifizieren von Softwarekomponenten?','Entwurf','Analyse','Implementierung','Testen',1,'Software-Engineering','Beispielerklärung','geprüft'),(25,'Welches Gewürz ist auch als `flüssiges Gold` bekannt und wird oft in der mediterranen Küche verwendet?','Olivenoel','Thymian','Rosmarin','Oregano',1,'testing','Beispielerklärung','geprüft'),(26,'Welcher Planet ist der vierte in unserem Sonnensystem, auch als der Rote Planet bekannt?','Mars','Venus','Jupiter','Saturn',1,'testing','Beispielerklärung','geprüft'),(27,'Welcher berühmte Physiker formulierte die Relativitätstheorie?','Albert Einstein','Isaac Newton','Galileo Galilei','Stephen Hawking',1,'testing','Beispielerklärung','geprüft'),(28,'Welche Hauptfarben ergeben sich durch die Mischung der Primärfarben Rot, Blau und Gelb?','Rot, Blau, Gelb','Türkis, Magenta, Gelb','Schwarz, Weiß, Grau',' Grün, Violett, Orange',1,'testing','Beispielerklärung','geprüft'),(29,'Welches bekannte Naturphänomen tritt auf, wenn Sonnenlicht durch Wassertropfen in der Atmosphäre gebrochen und reflektiert wird?','Regenbogen','Nordlicht','Halo-Effekt','Meteoritenschauer',2,'testing','Beispielerklärung','geprüft'),(30,'Welches Instrument wird oft mit dem Jazz assoziiert und erzeugt Töne durch das Anschlagen von Saiten mit einem Plektrum?','Gitarre','Klavier','Trompete','Saxophon',3,'testing','Beispielerklärung','geprüft'),(31,'Welches der sieben Weltwunder der Antike ist das einzige, von dem heute noch Überreste existieren?','Die Pyramiden von Gizeh','Der Koloss von Rhodos','Der Leuchtturm von Alexandria','Die Hängenden Gärten von Babylon',3,'testing','Beispielerklärung','geprüft'),(32,'Welcher berühmte Schriftsteller schuf die Figur Sherlock Holmes?','Sir Arthur Conan Doyle','Charles Dickens','Agatha Christie','J.K.Rowling',2,'testing','Beispielerklärung','geprüft'),(33,'Welcher Ozean ist der größte der Welt?','Pazifischer Ozean','Atlantischer Ozean','Indischer Ozean','Südlicher Ozean',1,'testing','Beispielerklärung','geprüft'),(34,'Welche berühmte Gemälde-Madame ist bekannt für ihren mysteriösen Blick und ihr leicht lächelndes Gesicht?','Die Mona Lisa','Die Dame mit dem Hermelin','Das Mädchen mit dem Perlenohrring','Die Venus von Urbino',1,'testing','Beispielerklärung','geprüft'),(35,'Welches chemische Element hat das Symbol Au auf dem Periodensystem und wird oft zur Herstellung von Schmuck verwendet?','Gold','Silber','Platin','Kupfer',3,'testing','Beispielerklärung','geprüft'),(36,'Welche Hauptstadt wird als Stadt der Liebe bezeichnet und ist bekannt für den Eiffelturm?','Paris','Madrid','Wien','Rom',1,'testing','Beispielerklärung','geprüft'),(37,'Welche bekannte Naturkatastrophe wird durch das abrupte Verschieben von Erdplatten verursacht und führt oft zu Erdbeben?','Plattentektonik','Tsunami','Vulkanausbruch','Tornado',1,'testing','Beispielerklärung','geprüft'),(38,'Welches astronomische Phänomen tritt auf, wenn der Mond zwischen der Sonne und der Erde steht und das Sonnenlicht blockiert?','Sonnenfinsternis','Mondfinsternis','Haloschein','Kometenschweif',1,'testing','Beispielerklärung','geprüft'),(39,'Welcher weltbekannte Komponist ist für seine neunte Sinfonie, die Ode an die Freude, bekannt?','Ludwig van Beethoven','Wolfgang Amadeus Mozart','Johann Sebastian Bach','Franz Schubert',3,'testing','Beispielerklärung','geprüft'),(40,'Welche der folgenden Formeln ist äquivalent zu NOT(A => B)?','A AND NOT(B)','A OR NOT(B)','NOT(A) OR B','NOT(A) AND B',1,'Theoretische Informatik','Beispielerklärung','geprüft'),(41,'Eine Formel ist genau dann erfüllbar, wenn ...','ihre Verneinung falsifizierbar ist.','ihre Verneinung unerfüllbar ist.','ihre Verneinung allgemeingültig ist.','ihre Verneinung erfüllbar ist.',1,'Theoretische Informatik','Beispielerklärung','geprüft'),(42,'Welche der folgenden Mengen von Operatoren bildet eine vollständige Basis für die Aussagenlogik?','NAND','NOT','AND','AND und OR',1,'Theoretische Informatik','Beispielerklärung','geprüft'),(43,'Wie viele Wahrheitswerte werden bei der Umsetzung der Aussagenlogik in Programmiersprachen wie Java genutzt?','3','4','0','2',1,'Theoretische Informatik','Beispielerklärung','geprüft'),(44,'Gegeben die beiden Terme P(f(x)) und P(y). Wie lautet ein allgemeinster Unifikator dieser beiden Terme?','y -> f(x)','x -> 1, y -> f(1)','f(x) -> y','Es gibt keinen allgemeinsten Unifikator dieser Terme.',1,'Theoretische Informatik','Beispielerklärung','geprüft'),(45,'Kann man mit dem Resolutionsverfahren für die Prädikatenlogik mit grundlegender Arithmetik für alle allgemeingültigen Formeln deren Allgemeingültigkeit nachweisen?','Nein, gemäß den Gödelschen Unvollständigkeitssätzen','Nur dann, wenn es einen allgemeinsten Unifikator für die Formel gibt.','Ja, gemäß dem Gödelschen Vollständigkeitssatz.','Nein, das Resolutionsverfahren dient nur zum Nachweis der Erfüllbarkeit.',2,'Theoretische Informatik','Beispielerklärung','geprüft'),(46,'Was bedeutet es, wenn ein endlicher Automat beim Einlesen eines Wortes nicht in einem finalen Zustand ist, wenn er das Wortende erreicht?','Das Wort gehört nicht zu der vom Automaten definierten Sprache.','Das Wort enthält Zeichen, die nicht zum Alphabet gehören.','Das Wort muss ergänzt werden, bevor eine Aussage über die Sprachzugehörigkeit möglich ist.','Das Wort gehört zu der vom Automaten definierten Sprache.',2,'Theoretische Informatik','Beispielerklärung','geprüft'),(47,'Wie wird ein Automat bezeichnet, bei dem in einem Zustand verschiedene Übergänge möglich sind und der daher einen erfolgreichen Übergang errät (sofern es einen gibt)?','nicht-deterministisch','endlich','Automat mit Epsilon-Kanten','Diese Eigenschaft ist bei Automaten nicht erlaubt',2,'Theoretische Informatik','Beispielerklärung','geprüft'),(48,'Welches ist die schwächste Grammatik in der Chomsky-Hierarchie?','Reguläre Grammatiken','Kontextfreie Grammatiken','Kontextsensitive Grammatiken','Rekursiv aufzählbare Grammatiken',2,'Theoretische Informatik','Beispielerklärung','geprüft'),(49,'Wozu dient eine lexikalische Analyse?','Identifizieren der Bestandteile einer Zeichenkette','Suche in einem Lexikon','Zerlegung eines Wortes in seine einzelnen Zeichen','Übersetzung eines Wortes in eine andere Sprache',2,'Theoretische Informatik','Beispielerklärung','geprüft'),(50,'Welche der folgenden Sprachklassen ist am umfangreichsten?','kontextsensitive Sprachen','kontextfreie Sprachen','Typ-2-Sprachen','von endlichen Automaten erkannte Sprachen',2,'Theoretische Informatik','Beispielerklärung','geprüft'),(51,'Welchen Typ hat die folgende Grammatik: S->A;A->aBc;aA->ac;B->baB;B->Aa','kontextsensitiv','Typ 0','regulär','kontextfrei',2,'Theoretische Informatik','Beispielerklärung','geprüft'),(52,'Drei der folgenden Beschreibungen definieren die gleiche Klasse von formalen Sprachen. Welche Beschreibung gehört nicht dazu?','Die Sprache wird durch eine Grammatik definiert, bei der alle Regeln eine der Formen A->aB oder A->Ba haben.','Die Sprache wird durch einen endlichen Automaten akzeptiert.','Die Sprache wird durch eine reguläre Grammatik erzeugt.','Die Sprache wird durch einen regulären Ausdruck definiert.',2,'Theoretische Informatik','Beispielerklärung','geprüft'),(53,'Ist die Ackermann-Funktion berechenbar und primitiv-rekursiv?','Die Ackermann-Funktion ist berechenbar, aber nicht primitiv-rekursiv.','Die Ackermann-Funktion ist primitiv-rekursiv, aber nicht berechenbar.','Die Ackermann-Funktion ist weder berechenbar noch primitiv-rekursiv.','Die Ackermann-Funktion ist berechenbar und primitiv-rekursiv.',3,'Theoretische Informatik','Beispielerklärung','geprüft'),(54,'Alle mit einem Programm mit WHILE-Schleifen berechenbaren Funktionen sind ...','mü-rekursiv.','primitiv-rekursiv.','mit einem endlichen Automaten berechenbar.','mit FOR-Schleifen berechenbar',3,'Theoretische Informatik','Beispielerklärung','geprüft'),(55,'Alle mit der Typ-0-Grammatik G erzeugten Worte haben mindestens die Länge 10. Diese Aussage ist','für manche, aber nicht alle Grammatiken G entscheidbar','für alle Grammatiken G entscheidbar','für alle Grammatiken G wahr','für keine Grammatik G entscheidbar',3,'Theoretische Informatik','Beispielerklärung','geprüft'),(56,'Welche der folgenden Aufgaben ist nicht NP-vollständig?','Primfaktorzerlegung','Zerlegungsproblem','Hamilton-Kreise','Problem des Handlungsreisenden',3,'Theoretische Informatik','Beispielerklärung','geprüft'),(57,'Bei welcher der folgenden Varianten von Petri-Netzen kann eine Transition an einer Stelle mehrere Marken verbrauchen?','Platz-Transitions-Netze','Bedingungs-Ereignis-Netze','Workflow-Netze','allgemeine Petri-Netze',3,'Theoretische Informatik','Beispielerklärung','geprüft'),(58,'Von einem Petri-Netz weiß man, dass es lebendig ist. Welche weitere Eigenschaft des Netzes kann man daraus schließen?','Deadlock-Freiheit','endlicher Erreichbarkeitsgraph','Sicherheit','Terminierung',1,'Theoretische Informatik','Beispielerklärung','geprüft'),(59,'Das Gleichheitsproblem für Erreichbarkeitsmengen von Petri-Netzen ','ist nicht entscheidbar','liegt in NP','liegt in NPSPACE','liegt in EXPSPACE',1,'Theoretische Informatik','Beispielerklärung','geprüft'),(60,'Für ein gegebenes sicheres Petri-Netz soll der Erreichbarkeitsgraph berechnet werden. Diese Aufgabe ','liegt in EXP','liegt in NP','liegt in P','ist nicht entscheidbar',1,'Theoretische Informatik','Beispielerklärung','geprüft'),(61,'Welche Auswirkungen hat es auf die T-Invarianten eines Petri-Netzes, wenn man eine zusätzliche Marke an einen Platz des Netzes legt?','keine Auswirkungen','Es ist keine allgemeingültige Aussage möglich','Bestehende T-Invarianten fallen weg','Es gibt zusätzliche T-Invarianten',2,'Theoretische Informatik','Beispielerklärung','geprüft');
/*!40000 ALTER TABLE `fragenkatalog` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-31 18:54:36