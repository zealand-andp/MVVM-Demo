# MVVM-Demo
Simple example of Android-app demonstrating Observer, MVVM, and Clean Architecture design patterns.

# Opgavebeskrivelse
1. Lav en tom Android-app (benyt eksempelvis Empty Activity template).
   Gem projektet på GitHub og sørg for at committe og pushe for hvert punkt der bliver færdiggjort.

2. Tilføj et TextView og et EditText på skærmen sammen med en Button.
   Når knappen aktiveres skal labelen (TextView) opdateres med indholdet fra tekstfeltet (EditText).

3. Separer dataopbevaring ud af View så den opbevares og vedligeholdes i en separat Model-klasse. 
   Placer modelklassen i sin egen "model"-package. Klassen kan eksempelvis hedde "Model".
   Placer samtidigt View-klassen i sin egen "view"-package. Klassen kan eksempelvis hedde "AndroidView" (men kan også blot forblive navngivet som "MainActivity").

4. Implementer Observable-funktionalitet for Model-klassen,
   så labelen først bliver opdateret når Model-klassen signalerer at der er sket en ændring,
   dvs. som det sidste i Model-klassens set-metode.

   Anvend eksempelvis java.util.Observer og java.util.Observable til at realisere Observer-mønstret. 
   (Vi ser stort på at de er blevet deprecated i Java 9.)

   Sørg for at der ikke er afhængighed fra Model-klassen til View-klassen, 
   dvs. at den ikke må have direkte kendskab til View-klassen. 
   (Den må kun kende View-klassen som en Observer.)

5. Tilpas View-klassen til at præsentere transformeret data i View'et. 
   Den nye funktionalitet skal som udgangspunkt wrappe Model-klassen
   og kan eksempelvis sættes til at præsentere modellens data i ren lower case. 

   androidx.lifecycle.MutableLiveData kan evt. anvendes
   til at facilitere nem Observer-funktionalitet i View-klassen. 

   Sørg for at der stadig ikke er afhængighed fra Model-klassen til View-klassen.

6. Opret en selvstændig ViewModel-klasse til at præsentere transformeret data til View'et. 
   Den konkrete ViewModel-klasse skal som udgangspunkt wrappe Model-klassen 
   og kan eksempelvis sættes til at præsentere modellens data i ren lower case. 
   I givet fald kan ViewModel-klassen navngives "LowerCasePresenter".
   ViewModel-klassen placeres i "view"-package.

   Bemærk at ViewModel teknisk set hører til i 3. inderste ring af vores Clean Architecture. 
   (Fordi den er en Presenter.)
   Dvs. at ViewModel ikke bør være afhængig af et specifikt UI-framework (som fx Android). 
   Det er derfor nødvendigt at lave en anden Observable-funktionalitet end den som blev benyttet i pkt. 5.

   Sørg for at der ikke er afhængighed fra ViewModel-klassen til View-klassen 
   og at der heller ikke er afhængighed fra Model-klassen til ViewModel-klassen.

7. Persister data i en database, der ikke er Firebase,
   benyt eksempelvis SQLite. 
   Sørg for at placere databasekoden i en separat "persistence"-package. 
   Sørg for at afhængighederne overholder guidelines for Clean Architecture, 
   dvs. at Model ikke må være afhængig af noget i "persistence"-package.

   Hint: Det er nødvendigt at anvende Observer-mønstret
   for at opdage når der er ændringer i Model.

   Næste gang programmet startes skal det vise den tekst der var gældende da programmet blev lukket.

8. Skift databasen ud med Firebase. 
   Det bør kunne gøres uden at ændre i "model" og "view". 
   Separer de to database-anvendelser i separate subpackages til "persistence", 
   eksempelvis "sqlite" og "firebase".

   Når flere personer anvender samme app samtidigt bør teksten opdateres simultant på samtlige devices.

9. Lav et JavaFX-projekt med samme funktionalitet som Android-app'en. 
   Placer Android-View'et i en separat "view.android"-subpackage.
   Det bør være muligt at genanvende alt i projektet undtagen "view.android"-pakken. 
   Placer det JavaFX-View i en separat "view.javafx"-subpackage.

   Brugsoplevelsen bør være lige som beskrevet i punkt 7.

10. Lav en sidste ViewModel-klasse som skal præsentere modellens data uden transformation, 
    den kan eksempelvis navngives "TrueCasePresenter" 
    og placeres ligeledes i "view"-package.

    Benyt den nye ViewModel-klasse i JavaFX-app'en, men ikke i Android-app'en. 
    Brug opsætningen til at teste at de to ViewModels præsenterer transformerede data
    uden at påvirke den underliggende model.
    
NB: For hver punkt/milepæl skal du huske at committe og pushe dine ændringer til GitHub.

# Vejledende løsning
Bemærk at hver release/tag i projektet repræsenterer en milepæl. Det er derfor nemt at hoppe til en version som kun viser præcist et bestemt trin.

0. Identificer teknologikrav i opgaven: 
   1. Android-app
   2. GitHub-repository
   3. java.util.Observer og jave.util.Observable
   4. androidx.lifecycle.MutableLiveData
   5. SQLite
   6. Firebase
   7. JavaFX-app

1. Opret nyt Android-projekt i Android Studio
   1. Brug Empty Activity-template ved oprettelsen
   2. Kald projektet "MVVM Demo"
   3. Angiv et passende package name
   4. Angiv en passende save location
   5. Sæt minimum API level til API 15 som foreslået  
      (Minimum API 14 er påkrævet for at benytte Firebase)
   6. Tilvælg anvendelse af androidx.* artifacts  
      (En delopgave lægger op til anvendelse af androidx.lifecycle.MutableLiveData)
   7. Del projektet på GitHub med en passende beskrivelse
   8. Kør app'en for at verificere at alt er sat rigtigt op

2. Tilføj UI-elementer og grundlæggende funktionalitet
   1. Fjern "Hello World" TextView
   2. Tilføj ny TextView
   3. Tilføj ny EditText
   4. Tilføj ny Button
   5. Angiv passende constraints for størrelser
   6. Commit den ønskede UI til Git med en passende commit message
   7. Omdøb id for TextView til outputView
   8. Omdøb id for EditText til inputText
   9. Omdøb id for Button til enterButton
   10. Opret en metode i MainActivity til håndtering af onClick for enterButton
   11. Commit den oprettede funktionalitet til Git med en passende commit message

3. Indfør lagdeling af view og model
   1. Opret view package
   2. Flyt MainActivity til view package
   3. Omdøb MainActivity til AndroidView
   4. Opret model package
   5. Opret Model-klasse indeholdende en data-String med getter/setter
   6. Initialiser data med en passende værdi
   7. Tilpas onCreate og enterData i AndroidView til at benytte Model

4. Implementer Observer-mønster
   1. Nedarv java.util.Observable interface i Model
   2. Tilpas setData i Model til at anvende Observable til at notificere observere
   3. Tilpas enterInput så den ikke længere opdaterer outputView
   4. Implementer java.util.Observer i en inner class i AndroidView
   5. Lav update i inner class så den opdaterer outputView

5. Implementer ViewModel
   1. Opret AndroidLowerCaseViewModel-klasse der nedarver fra androidx.lifecycle.ViewModel
   2. Lav et field, model, med det Model-objekt der skal wrappes
   3. Lav et field, presentableData, med det MutableLiveData<String> som skal præsenteres
   4. Implementer java.util.Observer i AndroidLowerCaseViewModel
   5. Sæt update-metoden til at opdatere presentableData, herunder transformere til lower case
   6. Lav en getter som returnerer presentableData
   7. Lav en setter som videredelegerer opdatering til model
   8. Gør AndroidView afhængig af AndroidLowerCaseViewModel i stedet for Model
   9. Implementer androidx.lifecycle.Observer<String> og observer på presentableData 
   10. Sæt onChanged til at opdatere outputView

6. Omform ViewModel til en ren Presenter
   1. Omdøb AndroidLowerCaseViewModel til LowerCasePresenter
   2. Ændr presentableData til typen String
   3. Sæt LowerCasePresenter til at nedarve fra java.util.Observable i stedet for ViewModel
   4. Tilpas observeModel så opdateringer bliver videreformidlet til observere
   5. Tilpas AndroidView til at bruge LowerCasePresenter med java.util.Observer/Observable
   6. Konstater at milepæl 5 ser ud at være totalt trolling fra din underviser, 
      men hey, nu kender du også Android Lifecycle Architecture! :-)

7. Tilføj persistens med SQLite
   1. Opret en MVVMDemoSQLiteHelper-klasse som nedarver fra android.database.sqlite.SQLiteOpenHelper
   2. Placer MVVMDemoSQLiteHelper i persistence.android package fordi den er Android-specifik
   3. Opret konstanter i MVVMDemoSQLiteHelper som kan bruges i SQL statements
   4. Lav en passende onCreate-metode i MVVMDemoSQLiteHelper som skaber en ny database
   5. Lav evt. en passende onUpgrade-metode i MVVMDemoSQLiteHelper (kommer ikke i anvendelse)
   6. Opret en MVVMDemoSQLiteHandler-klasse som ligeledes placeres i persistence.android package
   7. MVVMDemoSQLiteHandler skal implementere en java.util.Observer for at observere Model
   8. Lav en passende readData-metode som læser data fra databasen
   9. Lav en passende writeData-metode som skriver data til databasen
   10. Flyt initialisering af MVVMDemoSQLiteHandler ud i AndroidView (som har den nødvendige Context)

8. Tilføj persistens med Firebase
   1. Gå til https://firebase.google.com/docs/android/setup 
   2. Klik på "Sign into Firebase" og log ind med din Google-konto
   3. Følg proceduren for at tilføje Firebase til projektet
   4. Opsæt Cloud Firestore til projektet med en simpel collection der kan indeholde den nødvendige string
   5. Opsæt Realtime Database til projektet med en simpel string (husk at ændre Rules så der er adgang)
   6. Opret en FirebaseHandler-klasse som placeres i persistence package
   7. FirebaseHandler skal implementere en java.util.Observer for at observere Model
   8. FirebaseHandler skal også implementere en ValueEventListener for at observere på Firebase
   9. onDataChange-metoden i ValueEventListener skal opdatere Model
   10. Lav en writeData-metode som skriver data til databasen
   11. Placer initialisering af FirebaseHandler i AndroidView 
   12. Hæv minimum API level til 16 (API level 14 viste sig at være utilstrækkeligt)
