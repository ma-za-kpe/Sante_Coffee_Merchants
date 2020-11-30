# Sante_Coffee_Merchants
![](media/sante.png)

## About
An easy to use mobile application for the regional managers at sante merchants.

## EXAMPLE JSON
`{
     "Branch": {
         "id":"BRNCH001",
         "name":"BRANCH01",
         "manager":{
             "name": "maku"
         },
         "farmers": [
             {
                 "phone-number": "+25677823456",
                 "birth-certificate":{
                     "name": "popo",
                     "dob": "11/11/1992"
                 },
                 "national-id-num":{
                     "surname": "popo",
                     "givenname": "popopo",
                     "nationality": "ugandan",
                     "sex": "F",
                     "dob": "11/11/1992",
                     "card-no": "000999888",
                     "date-of-expiry": "12/11/2028",
                     "ID-num": "CHHHTTTYTYS&&&BS777"
                 }
             }, {
                 "farmer_id":"FRMR002",
                 "phone-number": "+25677823456",
                 "birth-certificate":{
                     "name": "popo",
                     "dob": "11/11/1992"
                 },
                 "national-id-num":{
                     "surname": "popo",
                     "givenname": "popopo",
                     "nationality": "ugandan",
                     "sex": "F",
                     "dob": "11/11/1992",
                     "card-no": "000999888",
                     "date-of-expiry": "12/11/2028",
                     "ID-num": "CHHHTTTYTYS&&&BS777"
                 }
             }
         ]
         }
 }`

## How to use this project from your phone
- download from this link [apk](https://drive.google.com/file/d/1Z20djYm3wUTAh16ytFD5gySrOhZxdNvN/view?usp=sharing)
- You will have to restart the app after adding a new farmer, MINOR LIVE DATA BUG.

## How to use this project from your laptop
- clone this repo
- Build and run the project.
- Incase you get a firebase error, 

## FIREBASE
- Create a new firebase account, add authentication to your project, replace the google services json file with you own. 
- [HOw TO GUIDE ...](https://firebase.google.com/docs/auth/android/google-signin)

## TODO
- optimisations and code clean up(code smells)
- update feature
- Unit testing
- fix minor bug with live data in Farmer Doa.

## Contact
shoot me an email at:- [maku](makpalyy@gmail.com)


