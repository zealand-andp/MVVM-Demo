# MVVM-Demo
Simple example of Android-app demonstrating Observer, MVVM, and Clean Architecture design patterns.

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
