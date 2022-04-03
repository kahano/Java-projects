import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;
import java.util.ArrayList;

import java.lang.UnsupportedOperationException;

class Oblig1{

    //********************* oppgave1 ***********************

    public static void bytt(int[] a, int i,int k){ // hejelpe metode for maks metode
       int change = a[i];
       a[i] = a[k];
       a[k] = change;
       }


    public static void snu(int[] a, int v, int h){
        while(v < h){
            bytt(a,v++,h--);
        }
    }




    public static void snu(int[]a,int v){
           snu(a,v,a.length-1);
    }
    public static void snu(int[] a){
        snu(a,0,a.length-1);
    }

    public static boolean nestePermutasjon(int[] a){
       int i = a.length -2;
           //int[] verdier = {3,1,4,9,7,10,8,6,5,2};
       while(i>= 0 && a[i] > a[i+1]){
                 i--;
            }
            if(i < 0){
              return false;
             }
             else{

                 int j = a.length -1;
                 while(j >= 0 && a[j] < a[i]){
                       j--;

                 }
                 bytt(a,i,j);
                 snu(a,i+1);
                 return true;
            }

    }



        public static int maks(int[] a){

            if(a == null){
               throw new NoSuchElementException("Tabellen a er tom!");

            }


            if(a.length < 1 ){
               throw new NoSuchElementException("Tabellen a er ullovlig aa bruke !");

            }

            int storst;
            for(int i = 0; i < a.length-1;i++){
                if (a[i+1] < a[i]) {
                bytt(a,i,i+1);
                }
            }
            storst = a[a.length-1];
            return storst;


        }
         // kjøring av maks() metoode tok 364 millisekunder og den er gjennmsnittlig ikke bedre enn de andre maks metodene som vi har sett i kompendiet.
         //Maks1-metode tok 128 millisekunder for å kjøre
         //Maks2-metode tok 70 millisekunder for å kjøre
         //Maks3-metode tok 48 millisekunder for å kjøre







       public static int[] randPerm(int n){ // kildekode: programkode 1.1.8a , kapittel 1.1.
               // hjelpemetode som skal brukes for å finne gjennomsnittlig antall ombyttinger.
           int[] a = new int[n];

           for (int i = 0; i<n ; i++) a[i] = i+1;

           Random r = new Random();

           for(int k = n-1 ; k>0 ; k--) {
               int i = r.nextInt(k + 1);
               bytt(a,k,i);
           }
           return a;





      }

      public NoSuchElementException call() throws Exception {
          return new NoSuchElementException();
      }

      public static int ombyttinger(int[] a) {
          if(a == null){
               throw new NoSuchElementException("Tabellen a er tom!");

            }


            if(a.length < 1 ){
               throw new NoSuchElementException("Tabellen a er ullovlig aa bruke !");


            }

            int antall_ombytting = 0;
            int storst= 0;

            for(int i = 0; i < a.length-1;i++){
                storst = a[i];

                if (a[i+1] < storst) {
                    bytt(a,i,i+1);
                    antall_ombytting++;

                }
            }

            return antall_ombytting;

       }


    //public static void gjennomsnitt(int lengde,int antpermutasjoner){ // hjelpemetode for å finne gjennomsnitt av antall ombyttinger
        //int antall_ombytt = 0;
       // int str = lengde;

       // double gjennomsnitt = 0;
       // int indeks = 0;
       // int total = 0;

       // while(indeks < lengde){
       //     int[] verdier = randPerm(antpermutasjoner);
       //     antall_ombytt = ombyttinger(verdier);
       //     total+= antall_ombytt;
       //     indeks++;


       // }
       // gjennomsnitt = total/str;
       //System.out.println("gjennomsnitt: " + gjennomsnitt);

       //}

    ///// Oppgave 2 //////////////////////////////////////

       public static int[] sortering(int[] values){ // hjelpemetode som skal brukes senere på metode antallUlikeSortert
          for(int i = 0; i < values.length-1;i++){
             for(int j = i+1;j < values.length;j++){
                if(values[j] > values[i]){
                    bytt(values,i,j);
                }
             }
          }
          return values;
       }
      //hjelpe metode som sjekker for stigende rekkefølge sortering

       public static boolean sortering_stigende(int [] values){

         for(int i = 0; i < values.length-1;i++){

            for (int j= i+1; j < values.length; ++j) {

                if (values[j] < values[i]) {

                   return false;
                }

             }

          }
          return true;
         }

         public static int antallUlikeSortert(int[] a) {
            int antall_ulikeverdier = 0;

            if(a == null){
                throw new NoSuchElementException("Tabellen a er tom!");

            }
            if(a.length == 0){
               return 0;
            }
            else if(a.length == 1 ){
                return 1;
            }

            if(!sortering_stigende(a)){
                throw new IllegalStateException("Tabellen ikke er sortert ");
            }

            int[] sortert_liste = sortering(a);

            for (int i = 0; i < sortert_liste.length ; i++) {


                int k;
                for ( k = 0 ; k < i ; k++) {

                   if (sortert_liste[i] == sortert_liste[k]) {

                       break;
                   }


                 }
                 if (i == k) {
                     antall_ulikeverdier++;
                 }
              }
              return antall_ulikeverdier;



            }

    ///// Oppgave 3 //////////////////////////////////////
            public static int antallUlikeUsortert(int[] a) {
                int antall_ulikeverdier = 0;

                if(a == null){
                    throw new NoSuchElementException("Tabellen a er tom!");

                }
                if(a.length == 0){
                    return antall_ulikeverdier;
                }


                for (int i = 0; i < a.length ; i++) {


                   int k;
                   for ( k = 0 ; k < i ; k++) {

                       if (a[i] == a[k]) {

                           break;
                       }


                   }
                   if (i == k) {
                      antall_ulikeverdier++;
                   }
               }
               return antall_ulikeverdier;
            }

    ///// Oppgave 4 //////////////////////////////////////

            private static int parter0(int[] a, int v, int h, int skilleverdi) // kildekode : kompendiet , programkode 1.3.9 a)
            {
               while (true)                                  // stopper når v > h
              {
                while (v <= h && a[v] < skilleverdi) v++;   // h er stoppverdi for v
                while (v <= h && a[h] >= skilleverdi) h--;  // v er stoppverdi for h

                if (v < h) bytt(a,v++,h--);                 // bytter om a[v] og a[h]
                else  return v;  // a[v] er nåden første som ikke er mindre enn skilleverdi
              }
            }

            private static int sParter0(int[] a, int v, int h, int indeks) // kildekode: kompendiet, programkode 1.3.9 f)
            {
             bytt(a, indeks, h);           // skilleverdi a[indeks] flyttes bakerst
             int pos = parter0(a, v, h - 1, a[h]);  // partisjonerer a[v:h - 1]
             bytt(a, pos, h);              // bytter for å få skilleverdien på rett plass
             return pos;                   // returnerer posisjonen til skilleverdien
            }





            private static void quicksort(int[] values, int lav, int hoy){ //hjelpemetode for delsortering
                if(lav >= hoy){
                    return;
                }
                int midtpunkt = sParter0(values,lav,hoy,(lav+hoy)/2);
                quicksort(values,lav,midtpunkt-1);
                quicksort(values,midtpunkt+1,hoy);
            }
            public static void delsortering(int[] a) {
                 int venstre = 0;
                int hoyre = a.length-1;


                if(a.length==1){ // hvis arrayet har kun en verdi så gjør vi ingenting og hoppe over
                    return;
                }


                int indeks = 0;
                while( indeks < a.length){

                    while(hoyre >= venstre && Math.abs(a[venstre]) %2== 1 ){ // her i tilfellet verdiene vi har i hele arrayet er negative verdier eller påå venstre del av arrayet
                        // så bruker vi absolutt verdi av den negative verdie
                        venstre = venstre+1;


                    }

                    while( hoyre >= venstre && Math.abs(a[hoyre])%2 == 0  ){// her i tilfellet verdiene vi har i hele arrayet er negative verdier eller påå høyre del av arrayet
                        // så bruker vi absolutt verdi av den negative verdien
                          hoyre = hoyre-1;



                    }
                    if(venstre < hoyre){
                        bytt(a,venstre,hoyre);
                        venstre++;
                        hoyre--;

                    }
                    indeks++;


                }

                quicksort(a,0,hoyre);
                quicksort(a,venstre,a.length-1);



            }

    ///// Oppgave 5 //////////////////////////////////////
            public static void rotasjon(char[] a) {
                if(a.length ==0){ // hvis listen er tom eller har bare en element saa gjor metoden ingenting
                    return;
                }

                char[] b = new char[a.length];
                for(int i=0; i<a.length; i++){
                    b[i] = a[i];
                }
                for(int i=0; i<a.length; i++){
                    if(i == 0){
                        a[i] = b[a.length-1];
                    }
                    else{
                        a[i] = b[i-1];
                    }
                }
            }

    ///// Oppgave 6 //////////////////////////////////////
            public static void rotasjon(char[] a, int k)throws NoSuchElementException {
                if(a.length ==0){ // hvis listen er tom eller har bare en element saa gjor metoden ingenting
                    return;
                }

                if(k>a.length){
                    k=k%a.length;
                }
                if(k*-1> a.length){
                    k=k%a.length;
                }
                char[] b = new char[a.length];
                for(int i=0; i<a.length; i++){
                    b[i] = a[i];
                }
                    if(k>0) {
                        for(int i=0; i<a.length; i++){
                            if(i >= k){
                                a[i] = b[i-k];
                            }
                            else{
                                a[i] = b[a.length-k+i];
                            }
                        }
                    }else if(k==0 ){
                    }else{
                        int teller = 0;
                        for(int i=0; i<a.length; i++){
                            if(i+k*-1 >= a.length){
                                a[i] = b[teller];
                                teller++;

                            }
                            else{
                                a[i] = b[k*-1+i];
                            }
                        }
                    }
            }

    ///// Oppgave 7 //////////////////////////////////////
            /// 7a)
            public static String flett(String s, String t) {
                ArrayList<Character> char1= new ArrayList<>();
                ArrayList<Character> char2= new ArrayList<>();
                for(char ch1 : s.toCharArray()){
                    char1.add(ch1);
                }
                for(char ch2 : t.toCharArray()){
                    char2.add(ch2);
                }

                String tekst = "";

                int minimum = Math.min(char1.size(), char2.size());
                for(int i=0; i<minimum; i++){
                    tekst +=char1.get(i);
                    tekst +=char2.get(i);

                }

                if(char1.size() > minimum){
                    for(int i=minimum; i<char1.size(); i++){
                        tekst += char1.get(i);
                    }
                }
                if(char2.size()>minimum){
                    for(int i= minimum; i<char2.size(); i++){
                        tekst += char2.get(i);
                    }
                }
                return tekst;
            }

            /// 7b)
            public static String flett(String... s) {
                int i=0;
                String s1 = "";
                int lengde = 0;

                for(String ss : s){
                    char[] tekst = ss.toCharArray();
                    if(lengde < tekst.length){
                    lengde = tekst.length;
                    }
                }
                while(i<lengde){
                    for(String ss : s){
                        char[]tekst = ss.toCharArray();
                        if(tekst.length > i){
                            s1 = s1+tekst[i];
                        }
                    }
                    i++;
                }
                return s1;
            }

    ///// Oppgave 8 //////////////////////////////////////
            public static int[] indekssortering(int[] a) {
                int[][] res = new int[a.length][2];
               for (int i = 0; i < a.length; i++) {
                   res[i][0] = a[i]; // [1, 0]
                   res[i][1] = i;
               }



               Arrays.sort(res, (b, c) -> Integer.compare(b[0], c[0]));

               int[] indexes = new int[a.length];
               for (int j = 0; j < res.length; j++) {
                   indexes[j] = res[j][1]; // her skal jeg extracte indeksene av sorterte verdiene av tabellen a
               }
               return indexes;
            }

    ///// Oppgave 9 //////////////////////////////////////
            public static int[] tredjeMin(int[] a) {
                int[] array = new int[3];
               int indeks = 0;

               // her sjekker først og fremst om tabell a er fære enn 3 elementer
               if(a.length < 3){
                   throw new NoSuchElementException("Tabellen kan ikke brukes !");

               }


               while( indeks < array.length){
                   array[indeks] = a[indeks];
                   indeks++;
               }
               int[] liste = indekssortering(array);  // her kaller vi på indekssortering og lagrer den i  en hjelpetabell

               int forste_min = liste[0]; // her deklarer vi  de tre minste indekser ved å indiksere listen vi har fra forrige oppgave
               int andre_min = liste[1];
               int tredje_min = liste[2];
               int minstverdi = a[forste_min];
               int andre_minstverdi = a[andre_min];
               int tredje_minstverdi = a[tredje_min];


               // her sjekker jeg om rekkefølgen av indeksene er riktig basert på de tre minste verdier av tabell a
               if(minstverdi > andre_minstverdi && minstverdi < tredje_min){
                   forste_min = andre_min;
                   andre_min = forste_min;

               }

               if(minstverdi < tredje_minstverdi && andre_minstverdi > tredje_minstverdi){
                   andre_min = tredje_min;
                   tredje_min = andre_min;
               }

               for(int i = 3; i < a.length;i++){
                   if(a[i] < tredje_minstverdi){
                       if(a[i] < andre_minstverdi){

                           if(a[i] < minstverdi){ // her om verdien a[i] er mindre enn minst verdien så bytter vi på plass av de tre minste indekser
                             //og så setter vi første minste indeksen er lik i

                               tredje_min = andre_min;
                               tredje_minstverdi = a[tredje_min];
                               andre_min = forste_min;
                               andre_minstverdi = a[andre_min];
                               forste_min = i;
                               minstverdi = a[forste_min];


                            }

                            else{   // if a[i] > minstverdi her bytter vi  på indekser av andre og tredje indekser og sette indeksen for nest minstverdi lik i
                                tredje_min = andre_min;
                                tredje_minstverdi = a[tredje_min];
                                andre_min = i;
                                andre_minstverdi = a[andre_min];
                            }


                       }
                       else{ // if a[i] > andre_minst verdi så sette vi tredje minst indeks er lik i og tilordne også tilsutt verdien til den indeksen.
                           tredje_min = i;
                           tredje_minstverdi = a[tredje_min];

                       }

                   }



               }
               return new int[] {forste_min,andre_min,tredje_min};


            }

    ///// Oppgave 10 //////////////////////////////////////

            //public static int bokstavNr(char bokstav) {
                //throw new UnsupportedOperationException();
            //}

            public static boolean inneholdt(String a, String b) {
                if(a.length() == 0 ){
                    return true;
                }
                if(a == ""){
                    return true;
                }
                if(b == ""){
                    return false;
                }

                char[] sim_a = a.toCharArray();
                char[] sim_b = b.toCharArray();
                Arrays.sort(sim_a);
                Arrays.sort(sim_b);

                String ny_a = new String(sim_a);
                String ny_b = new String(sim_b);

                int matches = 0;
                int indeks = 0;

                // her skal vi itere gjennom streng ny_b og inni den stregen sjekker vi om indeksen match er mindre enn storrelse av ny_a streng
                // hvis ja så sjekker vi om character som ligger i indeksen av den ene streng matcher character som ligger i den andre strengen
                // og teller antall match av de like Character.

                while(indeks < ny_b.length() ){
                    if(matches < ny_a.length()){
                        if(ny_a.charAt(matches) == ny_b.charAt(indeks)){
                            matches++;
                        }


                    }
                    indeks++;
                }
                // hvis antall match stemmer med lengden av streng ny_a da er det riktig ellers er det nei
                int n = ny_a.length();
                if(matches == n ){
                    return true;
                }
                else{
                    return false;
                }
            }










    public static void main(String args[]){


          int antallFeil = 0;

          antallFeil += oppgave1();
          antallFeil += oppgave2();
          antallFeil += oppgave3();
          antallFeil += oppgave4();
          antallFeil += oppgave5();
          antallFeil += oppgave6();
          antallFeil += oppgave7();
          antallFeil += oppgave8();
          antallFeil += oppgave9();
          antallFeil += oppgave10();

          if (antallFeil == 0)
          {
            System.out.println("Gratulerer!! Du passerte testen!");
          }
          else
          {
            System.out.println("Må forbedres! Du har minst "
              + antallFeil + " feil eller svakheter!");

          }
          // main-metoden i class Program skal nå inneholde:
          int n = 100_000, antall = 2_000; // tabellstørrelse og gjentagelser
          long tid = 0;                    // for tidsmåling
          int a[] = randPerm(n);
          tid = System.currentTimeMillis();    // leser av klokken
          for (int i = 0; i < antall; i++) maks(a);
          tid = System.currentTimeMillis() - tid;
          System.out.println("Oblig.maks-metoden: " + tid + " millisek");
          //gjennomsnitt(100,40);










    }



        ///// Oppgave 1 //////////////////////////////////////

    public static int oppgave1(){

          int antallFeil = 0;

          boolean unntak = false;
          int[] tom = {};
          try
          {
            maks(tom);  // kaller maks-metoden
          }
          catch (Exception e)
          {
            unntak = true;
            if (!(e instanceof java.util.NoSuchElementException))
            {
              System.out.println("Opgave 1: a) Feil unntak for en tom tabell!");
              antallFeil++;
            }
          }

          if (!unntak)
          {
            System.out.println("Opgave 1: b) Kast unntak for en tom tabell!");
            antallFeil++;
          }

          int[] a = {3};
          int[] b = {5,2,8,4,7,6};
          int[] c = {5,4,3,2,1};
          int[] d = {1,2,3,4,5};
          if (maks(a) != 3 || maks(b) != 8 ||
              maks(c) != 5 || maks(d) != 5)
          {
            System.out.println("Oppgave 1: c) Maks-metoden: Feil resultat!");
            antallFeil++;
          }

          int[] e = {1,4,3,7,6,5,10,2,9,8};
          int[] f = {1,3,4,6,5,7,2,9,8,10};

          maks(e);
          if(!Arrays.equals(e,f))
          {
            System.out.println("Oppgave 1: d) Maks-metoden: feil i ombyttingene!");
            antallFeil++;
          }
          a = new int[] {6,5,4,3,2,1};
          b = new int[] {1,2,3,4,5};
          c = new int[] {4, 9, 3, 6, 1, 5, 7, 8, 10, 2};
          d = new int[] {2, 5, 8, 4, 3, 10, 1, 7, 6, 9};

          if (ombyttinger(a) != 5 || ombyttinger(b) != 0
          || ombyttinger(c) != 7 || ombyttinger(d) != 6)
          {
          System.out.println("Oppgave 1: e) Feil opptelling i ombyttingsmetoden!");
          antallFeil++;
          }
          return antallFeil;

    }

    public static int oppgave2()
  {
    int antallFeil = 0;

    int[] a = {};
    int[] b = {1};
    int[] c = {1,2,3,4,5,4};

    try
    {
      antallUlikeSortert(a);  // kaller metoden
      antallUlikeSortert(b);  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println
        ("Oppgave 2: a) Ikke unntak for tabell med 0 eller 1 verdi!");
      antallFeil++;
    }

    boolean unntak = false;

    try
    {
      antallUlikeSortert(c);  // kaller metoden
    }
    catch (Exception e)
    {
      unntak = true;
      if (!(e instanceof IllegalStateException))
      {
        System.out.println
          ("Oppgave 2: b) Feil unntak for en usortert tabell!");
        antallFeil++;
      }
    }

    if (!unntak)
    {
      System.out.println
        ("Oppgave 2: c) Kast et unntak for en usortert tabell!");
      antallFeil++;
    }

    int[] d = {1,1};
    int[] e = {1,2,3,4,5,6,7};
    int[] f = {1,1,2,2,2,3,4,4,5,6,6,6,6,7};

    if (antallUlikeSortert(a) != 0
        || antallUlikeSortert(b) != 1
        || antallUlikeSortert(d) != 1
        || antallUlikeSortert(e) != 7
        || antallUlikeSortert(f) != 7)
     {
       System.out.println("Oppgave 2: d) Metoden gir feil resultat!");
       antallFeil++;
     }
    return antallFeil;
  }

  ///// Oppgave 3 //////////////////////////////////////

 public static int oppgave3()
 {
   int antallFeil = 0;

   int[] a = {};   // skal ikke kaste unntak her!
   int[] b = {1};  // skal ikke kaste unntak her!
   int[] c = {1,1};
   int[] d = {6,2,4,6,9,1,4,9,10};
   int[] dkopi = {6,2,4,6,9,1,4,9,10};
   int[] e = {5,4,3,2,1};
   int[] f = {1,2,2,2,2,2,3};

   try
   {
     Oblig1.antallUlikeUsortert(a);  // kaller metoden
     Oblig1.antallUlikeUsortert(b);  // kaller metoden
   }
   catch (Exception ex)
   {
     System.out.println
       ("Oppgave 3: a) Ikke unntak for tabell med 0 eller 1 verdi!");
     antallFeil++;
   }

   if (Oblig1.antallUlikeUsortert(a) != 0
       || Oblig1.antallUlikeUsortert(b) != 1
       || Oblig1.antallUlikeUsortert(c) != 1
       || Oblig1.antallUlikeUsortert(d) != 6
       || Oblig1.antallUlikeUsortert(e) != 5
       || Oblig1.antallUlikeUsortert(f) != 3)
   {
     System.out.println("Oppgave 3: b) Metoden gir feil resultat!");
     antallFeil++;
   }

   if (!Arrays.equals(d,dkopi))
   {
     System.out.println("Oppgave 3: c) Metoden endrer tabellen!");
     antallFeil++;
   }

   return antallFeil;
  }


//******************************Oppgave 4 *************************

public static int oppgave4(){


  int antallFeil = 0;

  int[] a = {};   // skal ikke kaste unntak her!

  try
  {
    Oblig1.delsortering(a);  // kaller metoden
  }
  catch (Exception ex)
  {
    System.out.println
      ("Oppgave 4: a) Ikke unntak for en tom tabell!");
    antallFeil++;
  }

  a = new int[] {5};
  int[] b = {5};

  try
  {
    Oblig1.delsortering(a);  // kaller metoden
  }
  catch (Exception ex)
  {
    System.out.println
      ("Oppgave 4: b) Skal ikke kastes unntak her!");
    antallFeil++;
  }

  if (!Arrays.equals(a,b))
  {
    System.out.println
      ("Oppgave 4: c) Metoden gjør feil for en tabell en verdi!");
    antallFeil++;
  }

  a = new int[] {4};
  b = new int[] {4};

  try
  {
    Oblig1.delsortering(a);  // kaller metoden
  }
  catch (Exception ex)
  {
    System.out.println
      ("Oppgave 4: d) Skal ikke kastes unntak her!");
    antallFeil++;
  }

  if (!Arrays.equals(a,b))
  {
    System.out.println
      ("Oppgave 4: e) Metoden gjør feil for en tabell en verdi!");
    antallFeil++;
  }

  a = new int[] {4,2,6,10,8};
  b = new int[] {2,4,6,8,10};

  try
  {
    Oblig1.delsortering(a);  // kaller metoden
  }
  catch (Exception ex)
  {
    System.out.println
      ("Oppgave 4: f) Det går galt hvis det kun er partall!");
    antallFeil++;
  }

  if (!Arrays.equals(a,b))
  {
    System.out.println
      ("Oppgave 4: g) Det blir feil hvis det kun er partall!");
    antallFeil++;
  }

  a = new int[] {9,5,3,1,7};
  b = new int[] {1,3,5,7,9};

  try
  {
    Oblig1.delsortering(a);  // kaller metoden
  }
  catch (Exception ex)
  {
    System.out.println
      ("Oppgave 4: h) Det går galt hvis det kun er oddetall!");
    antallFeil++;
  }

  if (!Arrays.equals(a,b))
  {
    System.out.println
      ("Oppgave 4: i) Det blir feil hvis det kun er oddetall!");
    antallFeil++;
  }
  a = new int[] {1,2,3,4,5,6};
    b = new int[] {1,3,5,2,4,6};
    boolean flere = true;

    do
    {
      int[] c = a.clone();
      Oblig1.delsortering(c);

      if (!Arrays.equals(c,b))
      {
        System.out.println
          ("Oppgave 4: j) Gitt tabell:     " + Arrays.toString(a));
        System.out.println
          ("              Metoden skal gi: " + Arrays.toString(b));
        System.out.println
          ("              Du fikk:         " + Arrays.toString(c));

        antallFeil++;
        break;
      }
    } while (nestePermutasjon(a));

    a = new int[] {-4,-1,3,0,2,-3,-2,4,1};
    b = new int[] {-3,-1,1,3,-4,-2,0,2,4};

    try
    {
      Oblig1.delsortering(a);  // kaller metoden
    }
    catch (Exception ex)
    {
      System.out.println
        ("Oppgave 4: k) Skal ikke kastes unntak her!");
      antallFeil++;
    }

    if (!Arrays.equals(a,b))
    {
      System.out.println
        ("Oppgave 4: l) Metoden gjør feil for negative verdier!");
      antallFeil++;
    }

    if (antallFeil == 0)
    {
      a = randPerm(100000);
      long tid = System.currentTimeMillis();
      Oblig1.delsortering(a);
      tid = System.currentTimeMillis() - tid;

      for (int i = 0; i < 50000; i++)
      {
        if (a[i] != 2*i + 1)
        {
          System.out.println
            ("Oppgave 4: m) Feil resultat for denne tabellen!");
          antallFeil++;
          break;
        }
      }

      for (int i = 50000; i < 100000; i++)
      {
        if (a[i] != 2*(i - 49999))
        {
          System.out.println
            ("Oppgave 4: n) Feil resultat for denne tabellen!");
          antallFeil++;
          break;
        }
      }

      if (tid > 100)
      {
        System.out.println
          ("Oppgave 4: o) Tid: " + tid + ". Metoden er for ineffektiv!");
        System.out.println
          ("              Hint: Bruk en partisjoneringsteknikk!");
        antallFeil++;
      }
    }
    return antallFeil;




 }

 ///// Oppgave 5 //////////////////////////////////////

  public static int oppgave5()
  {
    int antallFeil = 0;

    char[] a = {};

    try
    {
      Oblig1.rotasjon(a);  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println
        ("Oppgave 5: a) Skal ikke kaste unntak for en tom tabell!!");
        antallFeil++;
    }

    char[] b = {'A'};
    char[] b0 = {'A'};
    Oblig1.rotasjon(b);
    if (!Arrays.equals(b, b0))
    {
      System.out.println("Oppgave 5: b) Feil hvis tabellen har ett element!");
      antallFeil++;
    }

    char[] c = {'A','B'};
    char[] c0 = {'B','A'};
    Oblig1.rotasjon(c);
    if (!Arrays.equals(c, c0))
    {
      System.out.println("Oppgave 5: c) Feil hvis tabellen har to elementer!");
      antallFeil++;
    }

    char[] d = {'A','B','C','D','E','F','G','H','I','J'};
    char[] d0 = {'J','A','B','C','D','E','F','G','H','I'};

    Oblig1.rotasjon(d);
    if (!Arrays.equals(d, d0))
    {
      System.out.println("Oppgave 5: d) Feil hvis tabellen har flere elementer!");
      antallFeil++;
    }

    return antallFeil;
  }

 //************************************* Oppgave 6*************************''
 ///// Oppgave 6 //////////////////////////////////////

  public static int oppgave6()
  {
    int antallFeil = 0;

    char[] a = {};

    try
    {
      Oblig1.rotasjon(a,1);  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println(e);
      System.out.println
        ("Oppgave 6: a) Skal ikke kaste unntak for en tom tabell!!");
        antallFeil++;
    }

    char[] b = {'A'};
    char[] b0 = {'A'};
    Oblig1.rotasjon(b,0);
    if (!Arrays.equals(b, b0))
    {
      System.out.println("Oppgave 6: b) Feil hvis tabellen har ett element!");
      antallFeil++;
    }

    Oblig1.rotasjon(b,1);
    if (!Arrays.equals(b, b0))
    {
      System.out.println("Oppgave 6: c) Feil hvis tabellen har ett element!");
      antallFeil++;
    }

    Oblig1.rotasjon(b,-1);
    if (!Arrays.equals(b, b0))
    {
      System.out.println("Oppgave 6: d) Feil hvis tabellen har ett element!");
      antallFeil++;
    }

    char[] c = {'A','B'};
    char[] c0 = {'B','A'};
    Oblig1.rotasjon(c,1);

    if (!Arrays.equals(c, c0))
    {
      System.out.println("Oppgave 6: e) Feil hvis tabellen har to elementer!");
      antallFeil++;
    }

    c = new char[] {'A','B'};

    Oblig1.rotasjon(c,-1);
    if (!Arrays.equals(c, c0))
    {
      System.out.println("Oppgave 6: f) Feil hvis tabellen har to elementer!");
      antallFeil++;
    }

    char[] d = {'A','B','C','D','E','F','G','H','I','J'};
    char[] d0 = {'G','H','I','J','A','B','C','D','E','F'};

    Oblig1.rotasjon(d,4);
    if (!Arrays.equals(d, d0))
    {
      System.out.println("Oppgave 6: g) Feil hvis tabellen har flere elementer!");
      antallFeil++;
    }

    d = new char[]{'A','B','C','D','E','F','G','H','I','J'};
    Oblig1.rotasjon(d,-6);
    if (!Arrays.equals(d, d0))
    {
      System.out.println("Oppgave 6: h) Feil hvis tabellen har flere elementer!");
      antallFeil++;
    }

    char[] x = new char[100_000];
    long tid = System.currentTimeMillis();
    Oblig1.rotasjon(x,99_999);
    tid = System.currentTimeMillis() - tid;

    if (tid > 20)
    {
      System.out.println("Oppgave 6: i) Metoden "
        + "er for ineffektiv. Må forbedres!");
      antallFeil++;
    }

    tid = System.currentTimeMillis();
    Oblig1.rotasjon(x,199_999);
    tid = System.currentTimeMillis() - tid;

    if (tid > 20)
    {
      System.out.println("Oppgave 6: j) Metoden "
        + "er for ineffektiv. Må forbedres!");
      antallFeil++;
    }

    tid = System.currentTimeMillis();
    Oblig1.rotasjon(x,50_000);
    tid = System.currentTimeMillis() - tid;

    if (tid > 20)
    {
      System.out.println("Oppgave 6: k) Metoden "
        + "er for ineffektiv. Må forbedres!");
      antallFeil++;
    }

    tid = System.currentTimeMillis();
    Oblig1.rotasjon(x,-40_000);
    tid = System.currentTimeMillis() - tid;

    if (tid > 20)
    {
      System.out.println("Oppgave 6: l) Metoden "
        + "er for ineffektiv. Må forbedres!");
      antallFeil++;
    }

    return antallFeil;
  }





  //****************************'' Oppgave 7 **************************'''

  ///// Oppgave 7 //////////////////////////////////////

 public static int oppgave7()
 {
   int antallFeil = 0;
   String s = null;

   try
   {
     s = Oblig1.flett("","");  // kaller metoden
   }
   catch (Exception e)
   {
     System.out.println(e);
     System.out.println
       ("Oppgave 7a: a) Skal ikke kaste unntak for to tomme tegnstrenger!!");
       antallFeil++;
   }

   if (s.compareTo("") != 0)
   {
     System.out.println
       ("Oppgave 7a: b) Svaret skal bli lik en tom streng!");
       antallFeil++;
   }

   try
   {
     s = Oblig1.flett("","AB");  // kaller metoden
   }
   catch (Exception e)
   {
     System.out.println(e);
     System.out.println
       ("Oppgave 7a: c) Skal ikke kaste unntak for en tom tegnstreng!!");
       antallFeil++;
   }

   if (s.compareTo("AB") != 0)
   {
     System.out.println
       ("Oppgave 7a: d) Svaret skal bli lik AB");
       antallFeil++;
   }

   try
   {
     s = Oblig1.flett("AB","");  // kaller metoden
   }
   catch (Exception e)
   {
     System.out.println(e);
     System.out.println
       ("Oppgave 7a: e) Skal ikke kaste unntak for en tom tegnstreng!!");
       antallFeil++;
   }

   if (s.compareTo("AB") != 0)
   {
     System.out.println
       ("Oppgave 7a: f) Svaret skal bli lik AB");
       antallFeil++;
   }

   s = Oblig1.flett("A", "BCDEF");

   if (s.compareTo("ABCDEF") != 0)
   {
     System.out.println
       ("Oppgave 7a: g) Svaret skal bli lik ABCDEF");
       antallFeil++;
   }

   s = Oblig1.flett("ABCDE", "F");

   if (s.compareTo("AFBCDE") != 0)
   {
     System.out.println
       ("Oppgave 7a: h) Svaret skal bli lik AFBCDE");
       antallFeil++;
   }

   s = Oblig1.flett("ACEGIK", "BDFHJLMN");

   if (s.compareTo("ABCDEFGHIJKLMN") != 0)
   {
     System.out.println
       ("Oppgave 7a: i) Svaret skal bli lik ABCDEFGHIJKLMN");
       antallFeil++;
   }

   String[] a = {};

   try
   {
     s = Oblig1.flett(a);  // kaller metoden
   }
   catch (Exception e)
   {
     System.out.println(e);
     System.out.println
       ("Oppgave 7b: a) Skal ikke kaste unntak for en tom tabell!");
       antallFeil++;
   }

   if (s.compareTo("") != 0)
   {
     System.out.println
       ("Oppgave 7b: b) Svaret skal bli lik en tom streng!");
       antallFeil++;
   }

   try
   {
     s = Oblig1.flett("","ABC","");  // kaller metoden
   }
   catch (Exception e)
   {
     System.out.println(e);
     System.out.println
       ("Oppgave 7b: c) Skal ikke kaste unntak for en tom streng!");
       antallFeil++;
   }

   if (s.compareTo("ABC") != 0)
   {
     System.out.println
       ("Oppgave 7b: d) Svaret skal bli lik ABC");
       antallFeil++;
   }

   s = Oblig1.flett("AM ","L","GEDS","ORATKRR","","R TRTE","IO","TGAUU");

   if (s.compareTo("ALGORITMER OG DATASTRUKTURER") != 0)
   {
     System.out.println
       ("Oppgave 7b: e) Svaret skal bli ALGORITMER OG DATASTRUKTURER!");
       antallFeil++;
   }

   s = Oblig1.flett("AFK","BGLP","CHMQT","DINRUW","EJOSVXY");

   if (s.compareTo("ABCDEFGHIJKLMNOPQRSTUVWXY") != 0)
   {
     System.out.println
       ("Oppgave 7b: f) Svaret skal bli ABCDEFGHIJKLMNOPQRSTUVWXY!");
       antallFeil++;
   }

   return antallFeil;
 }



  ///// Oppgave 8 //////////////////////////////////////

  public static int oppgave8()
  {
    int antallFeil = 0;

    int[] a = {};  // en tom tabell
    int[] indeks = null;

    try
    {
      indeks = Oblig1.indekssortering(a);  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println
        ("Opgave 8: a) Skal ikke kastes unntak for en tom tabell!");
      antallFeil++;
    }

    if (indeks == null || indeks.length > 0)
    {
      System.out.println
        ("Opgave 8: b) Indekstabellen skal ha lengde 0!");
      antallFeil++;
    }

    a = new int[] {5};

    try
    {
      indeks = Oblig1.indekssortering(a);  // kaller metoden
    }
    catch (Exception e)
    {
      System.out.println
        ("Opgave 8: c) Skal ikke kastes unntak her!");
      antallFeil++;
    }

    if (indeks == null || indeks.length == 0 || indeks.length > 1)
    {
      System.out.println
        ("Opgave 8: d) Indekstabellen skal ha lengde 1!");
      antallFeil++;
    }

    if (indeks[0] != 0)
    {
      System.out.println
        ("Opgave 8: e) indeks[0] skal være lik 0!");
      antallFeil++;
    }

    a = new int[] {1,2,3,4,5,6};
    int[] b = new int[] {1,2,3,4,5,6};
    boolean flere = true;

    do
    {
      int[] c = a.clone();
      indeks = Oblig1.indekssortering(c);

      if (!Arrays.equals(a,c))
      {
        System.out.println
          ("Oppgave 8: f) Tabellen før kallet:   " + Arrays.toString(a));
        System.out.println
          ("              Tabellen etter kallet: " + Arrays.toString(c));
        System.out.println
          ("              Parametertabellen skal ikke endres!");

        antallFeil++;
        break;
      }

      int[] d = new int[a.length];
      for (int i = 0; i < d.length; i++) d[i] = a[indeks[i]];

      if (!Arrays.equals(b,d))
      {
        System.out.println
          ("Oppgave 8: g) Feil i indekstabellen for a = " + Arrays.toString(a));

        antallFeil++;
        break;
      }

    } while (nestePermutasjon(a));

    a = new int[] {5,2,8,3,5,10,7,5,2,10,6};
    int[] c = a.clone();
    b = new int[] {2,2,3,5,5,5,6,7,8,10,10};
    indeks = Oblig1.indekssortering(a);
    int[] d = new int[a.length];
    for (int i = 0; i < d.length; i++) d[i] = a[indeks[i]];

    if (!Arrays.equals(a,c))
    {
      System.out.println
        ("Oppgave 8: h) Parametertabellen endres når den er lik");
      System.out.println
        ("            " + Arrays.toString(a));

      antallFeil++;
    }

    if (!Arrays.equals(b,d))
    {
      System.out.println
        ("Oppgave 8: i) Feil i indekstabellen for a = " + Arrays.toString(a));

      antallFeil++;
    }

    return antallFeil;
  }

  //*****************************Oppgave 9 ***********************


  public static int oppgave9()
 {
   int antallFeil = 0;

   boolean unntak = false;
   int[] test = {1,2};
   try
   {
     Oblig1.tredjeMin(test);  // kaller metoden
   }
   catch (Exception e)
   {
     unntak = true;
     if (!(e instanceof NoSuchElementException))
     {
       System.out.println("Opgave 9: a) Feil unntak!");
       antallFeil++;
     }
   }

   if (!unntak)
   {
     System.out.println
       ("Opgave 9: b) Det skal kastes unntak for tabeller med for få verdier!");
     antallFeil++;
   }

   int[] tabell = new int[] {1,2,3};
   boolean flere1 = true;

   while (flere1)
   {
     int[] c = Oblig1.tredjeMin(tabell);

     if (tabell[c[0]] != 1 || tabell[c[1]] != 2 || tabell[c[2]] != 3)
     {
       System.out.println("Oppgave 9: c) Feil for " + Arrays.toString(tabell));
       antallFeil++;
       break;
     }
     flere1 = nestePermutasjon(tabell);
   }

   int[] b = randPerm(10);
   int[] d = Oblig1.tredjeMin(b);

   if (b[d[0]] != 1 || b[d[1]] != 2 || b[d[2]] != 3)
   {
     System.out.println("Oppgave 9: d) Feil for " + Arrays.toString(b));
     antallFeil++;
   }

   int[] x = {6,3,9,1,10,5,2,8,4,7};
   int[] y = x.clone();
   Oblig1.tredjeMin(x);

   if (!Arrays.equals(x,y))
   {
     System.out.println
       ("Oppgave 9: e) Metoden gjør endringer i tabellen!");
     System.out.println("Tabellen før: " + Arrays.toString(y));
     System.out.println("Tabellen etter: " + Arrays.toString(x));
     antallFeil++;
   }

   int[] a = {1,2,3,4,5,6};
   boolean flere2 = true;

   while (flere2)
   {
     int[] c = Oblig1.tredjeMin(a);

     int m = c[0];
     int nm = c[1];
     int tm = c[2];

     if (a[m] != 1 || a[nm] != 2 || a[tm] != 3)
     {
       System.out.println("Oppgave 9: f) Feil for " + Arrays.toString(a));
       antallFeil++;
       break;
     }

     flere2 = nestePermutasjon(a);
   }

   a = randPerm(100_000);
   long tid = System.currentTimeMillis();
   Oblig1.tredjeMin(a);
   tid = System.currentTimeMillis() - tid;


   if (tid > 100)
   {
     System.out.println("Oppgave 9: g) Metoden er ineffektiv! Bruker du");
     System.out.println("metoden indekssortering på hele tabellen?");
     antallFeil++;
   }

   return antallFeil;
 }

 //*********************' Oppgave 10************************'
 public static int oppgave10()
 {
   int antallFeil = 0;
   boolean b = false;

   try
   {
     b = Oblig1.inneholdt("","");  // kaller metoden
   }
   catch (Exception e)
   {
     System.out.println(e);
     System.out.println
       ("Oppgave 10: a) Skal ikke kaste unntak for to tomme ord!!");
       antallFeil++;
   }

   if (b != true)
   {
     System.out.println
       ("Oppgave 10: b) Svaret skal bli lik true her!");
       antallFeil++;
   }

   try
   {
     b = Oblig1.inneholdt("","A");  // kaller metoden
   }
   catch (Exception e)
   {
     System.out.println(e);
     System.out.println
       ("Oppgave 10: c) Skal ikke kaste unntak for et tomt ord!!");
       antallFeil++;
   }

   if (b != true)
   {
     System.out.println
       ("Oppgave 10: d) Svaret skal bli lik true her!");
       antallFeil++;
   }

   try
   {
     b = Oblig1.inneholdt("A","");  // kaller metoden
   }
   catch (Exception e)
   {
     System.out.println(e);
     System.out.println
       ("Oppgave 10: e) Skal ikke kaste unntak for et tomt ord!!");
       antallFeil++;
   }

   if (b != false)
   {
     System.out.println
       ("Oppgave 10: f) Svaret skal bli lik false her!");
       antallFeil++;
   }

   b = Oblig1.inneholdt("ABBA", "ABBA");
   if (b != true)
   {
     System.out.println
       ("Oppgave 10: g) Svaret skal bli lik true her!");
       antallFeil++;
   }

   b = Oblig1.inneholdt("XYYX", "AAAAAAAYXXY");
   if (b != true)
   {
     System.out.println
       ("Oppgave 10: h) Svaret skal bli lik true her!");
       antallFeil++;
   }

   b = Oblig1.inneholdt("ABBA", "RABARBRA");
   if (b != true)
   {
     System.out.println
       ("Oppgave 10: i) Svaret skal bli lik true her!");
       antallFeil++;
   }

   b = Oblig1.inneholdt("ABBA", "BARBERER");
   if (b != false)
   {
     System.out.println
       ("Oppgave 10: j) Svaret skal bli lik false her!");
       antallFeil++;
   }

   b = Oblig1.inneholdt("ABBA", "AKROBAT");
   if (b != false)
   {
     System.out.println
       ("Oppgave 10: k) Svaret skal bli lik false her!");
       antallFeil++;
   }

   b = Oblig1.inneholdt("ØÅÅØ", "ØØÅØØ");
   if (b != false)
   {
     System.out.println
       ("Oppgave 10: l) Svaret skal bli lik false her!");
       antallFeil++;
   }

   b = Oblig1.inneholdt("ØÅÅØ", "ÅØØÅØ");
   if (b == false)
   {
     System.out.println
       ("Oppgave 10: m) Svaret skal bli lik true her!");
       antallFeil++;
   }

   char[] x = new char[100000];
   for (int i = 0; i < 50000; i++)
   {
     x[2*i] = 'X'; x[2*i + 1] = 'Y';
   }
   String t = String.copyValueOf(x);

   char[] y = new char[100000];
   for (int i = 0; i < 49999; i++)
   {
     y[2*i] = 'X'; y[2*i + 1] = 'Y';
   }
   y[99998] = 'Z'; y[99999] = 'Z';
   String s = String.copyValueOf(y);

   long tid = System.currentTimeMillis();
   b = Oblig1.inneholdt(s, t);
   tid = System.currentTimeMillis() - tid;

   if (tid > 100)
   {
     System.out.println
       ("Oppgave 10: n) Dette (" + tid + " ms) gikk sakte! Finn en bedre algoritme!");
       antallFeil++;
   }

   if (b != false)
   {
     System.out.println
       ("Oppgave 10: o) Svaret skal bli lik false her!");
       antallFeil++;
   }
   return antallFeil;
 }




}
