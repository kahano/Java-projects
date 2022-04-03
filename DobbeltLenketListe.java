import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.LinkedList;
import java.io.*;

public class DobbeltLenketListe<T> implements Liste<T>
{
  private static final class Node<T>   // en indre nodeklasse
  {
    // instansvariabler
    private T verdi;
    private Node<T> forrige, neste;

    private Node(T verdi, Node<T> forrige, Node<T> neste)  // konstruktør
    {
      this.verdi = verdi;
      this.forrige = forrige;
      this.neste = neste;
    }

    protected Node(T verdi)  // konstruktør
    {
      this(verdi, null, null);
    }

  } // Node

  // instansvariabler
  private Node<T> hode;          // peker til den første i listen
  private Node<T> hale;          // peker til den siste i listen
  private int antall;            // antall noder i listen
  private int endringer;   // antall endringer i listen

  // hjelpemetode
  private Node<T> finnNode(int indeks)
  {
    int storrelse = antall();
    int nodeindeks = 0;
    Node<T> peker = null;
    if(indeks < storrelse/2){
        peker = hode;
        while( nodeindeks < indeks){
            peker = peker.neste;
            nodeindeks++;
        }



    }
    else{
        int i = indeks;
        nodeindeks = antall()-1;
        peker = hale;
        while(i < nodeindeks){
            peker = peker.forrige;
            nodeindeks--;
        }

    }
    return peker;
  }

  // konstruktør
  public DobbeltLenketListe()
  {
    hode = hale = null;
    antall = 0;
    endringer = 0;
  }

  // konstruktør

  public DobbeltLenketListe(T[] a)
  {
    if(a == null){
        throw new NullPointerException("Tabellen a er null");
    }
    if(a.length == 0){
        return;
    }
    int indeks = 0;

    while(indeks < a.length){
        if(hode != null){
            if(a[indeks] != null){
                hale.neste = new Node<>(a[indeks],hale,null);
                hale = hale.neste;
                antall++;
            }


        }
        else{
            if(a[indeks] != null){
                hode = new Node<>(a[indeks],null,hale);
                hale = hode;
                antall++;
            }


        }
        indeks++;
    }


  }

  public  void fratilKontroll(int antall, int fra, int til) // hjelpemetode kildekode: kompendiet , obs! jeg gjøre litt endring på metoden
  {
    if (fra < 0)                                  // fra er negativ
      throw new IndexOutOfBoundsException
        ("fra(" + fra + ") er negativ!");

    if (til > antall)                          // til er utenfor tabellen
      throw new IndexOutOfBoundsException
        ("til(" + til + ") > antall(" + antall + ")");

    if (fra > til)                                // fra er større enn til
      throw new IllegalArgumentException
        ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
   }




  // subliste
  public Liste<T> subliste(int fra, int til)
  {
    fratilKontroll(antall,fra,til);

    T[] subliste = (T[]) new Object[Math.abs(fra-til)];
    Node<T>nynode = finnNode(fra);
    int nyindeks = 0;
    int indeks = fra;
    while(indeks < til){
        subliste[nyindeks] = nynode.verdi;
        nynode = nynode.neste;
        nyindeks++;
        indeks++;
    }
    DobbeltLenketListe<T> nyliste = new DobbeltLenketListe<>(subliste);
    return nyliste;

  }

  @Override
  public int antall()
  {
    return antall;
  }

  @Override
  public boolean tom()
  {
    return antall == 0;
  }

  @Override
  public boolean leggInn(T verdi)
  {
    if(verdi == null){
         Objects.requireNonNull(verdi);
    }
    Node<T> sistenode = null;
    if(!tom()){
        if(hale.neste == null){
            sistenode =  new Node(verdi);
            hale.neste = sistenode;
            sistenode.forrige = hale;
            hale = sistenode;
        }
    }
    else{
        sistenode = new Node(verdi);
        hode = hale = sistenode;

    }
    antall++;
    endringer++;
    return true;



  }

  @Override
  public void leggInn(int indeks, T verdi)

  {   Objects.requireNonNull(verdi, "null verdier ikke er akseptert");
      if(indeks < 0 || antall < indeks){
            throw new IndexOutOfBoundsException("ulovlig indeks ");
      }
      Node<T> ny = null;

      if(tom()){
          leggInn(verdi);
          return;
      }
      else if(indeks> 0 && indeks < antall() ){
            ny = new Node<>(verdi);
            Node<T> node = hode;
            int i = 1;
            while(i < indeks){
                node = node.neste;
                i++;
            }
            ny.forrige = node;
            ny.neste = node.neste;
            node.neste = ny;
            ny.neste.forrige = ny;

      }

      else if(indeks == 0){
          ny = new Node<>(verdi);
          if (hode == null){
             ny.neste = hode;
             hode.forrige = ny;
             hode = ny;
          }
          hode.forrige = ny;
          ny.neste = hode;
          hode = ny;


      }

      else{
          if (indeks == antall){

            leggInn(verdi);
            return;

         }

      }

      antall++;
      endringer++;

    }

  @Override
  public boolean inneholder(T verdi)
  {
    int indeks = indeksTil(verdi);
    for(int i = 0; i < antall;i++){
        if(i != indeks && indeks == -1){
            return false;
        }
    }
    return true;



  }

  @Override
  public T hent(int indeks)
  {
    indeksKontroll(indeks,false);
    return finnNode(indeks).verdi;

  }

  @Override
  public int indeksTil(T verdi)
  {

    Node<T> peker = hode;
    int lengde = antall();
    int indeks = 0;
    while(indeks < lengde){
        if(hent(indeks).equals(verdi)){
            return indeks;
        }
        peker = peker.neste;
        indeks++;

    }
    return -1;


  }

  @Override
  public T oppdater(int indeks, T nyverdi)

  {

    if(nyverdi != null){
        indeksKontroll(indeks,false);
        Node<T> current = finnNode(indeks);
        T aktueltverdi = hent(indeks);
        current.verdi = nyverdi;
        endringer++;
        return aktueltverdi;
    }
    else{
        throw new NullPointerException("Det aksepteres ikke nullverdier ");
    }

  }

  @Override
  public boolean fjern(T verdi)
  {

    if(verdi == null || antall == 0){
        return false;
    }

    if(!(inneholder(verdi))){ // hvis listen ikke inneholder verdien
        return false;

    }
    Node <T> temp = hode;
    int i;
    for(i = 0; i < antall();i++){
        if(!(temp.verdi.equals(verdi))){
            temp = temp.neste;
        }

    }

    if(antall() == 1 ){
        hode = hale = null;

    }

    else if(temp.forrige == null){
        hode.neste.forrige = null;
        hode = hode.neste;

    }

    else if(temp.neste == null){
        hale.forrige.neste = null;
        hale = hale.forrige;

    }

    else{

        temp.forrige.neste = temp.neste;
        temp.neste.forrige = temp.forrige;



    }


    antall--;
    endringer++;
    return true;

  }

  @Override
  public T fjern(int indeks)
  {
    if(indeks < 0 || indeks >= antall()){
            throw new IndexOutOfBoundsException("Ulovlig indeks ");
    }
    T beholdverdi = null;
    Node<T> temp = null;


    if(antall() == 1){
        beholdverdi = hode.verdi;
        hode = hale = null;

    }
    else if(indeks == 0){
        temp = hode;
        beholdverdi = temp.verdi;
        hode.neste.forrige = null;
         hode = hode.neste;




    }
    else if(indeks == antall()-1){
            temp = hale;
            beholdverdi = temp.verdi;
            hale.forrige.neste = null;
            hale = hale.forrige;



    }

    else{
           int k = 0;
            Node<T> peker = hode;
            while(k < indeks){
                peker = peker.neste;
                k++;
            }
            beholdverdi = peker.verdi;
            peker.forrige.neste = peker.neste;
            peker.neste.forrige = peker.forrige;




    }
    antall--;
    endringer++;
    return beholdverdi;
  }

  // @Override
  // public void nullstill()
  // {
  //
  //   // Node<T> current = hode;
  //   //
  //   //
  //   // while(current != null){
  //   //     Node<T> neste = current.neste;
  //   //     current.neste = null;
  //   //     current.forrige = null;
  //   //     current.verdi = null;
  //   //     current = neste;
  //   // }
  //   // hode = hale = null;
  //   // endringer++;
  //   // antall = 0;
  // }

  @Override
  public void nullstill(){
       int indeks = 0;
       int storrelse = antall(); // her setter jeg lengden av liste på en hjelpe variable for å beholde lengden av listen
       while(indeks < storrelse){
           fjern(0);
           indeks++;

       }



  }


  @Override
  public String toString()
  {
    if(tom()){
        return "[]";
    }
    StringBuilder streng = new StringBuilder("[");
    Node<T> peker = hode;


    while( peker != hale){

        streng.append(peker.verdi +", ");
        peker = peker.neste;

    }
    streng.append( peker.verdi + "]");
    return streng.toString();

  }

  public String omvendtString()
  {
    if(tom()){
          return "[]";
    }
    StringBuilder str = new StringBuilder("[");
    Node<T> peker = hale;

    while( peker != hode){

        str.append(peker.verdi +", ");
        peker = peker.forrige;
    }
    str.append( peker.verdi + "]");
    return str.toString();


  }

  public static <T> void sorter(Liste<T> liste, Comparator<? super T> c)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  @Override
  public Iterator<T> iterator()
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  public Iterator<T> iterator(int indeks)
  {
    throw new UnsupportedOperationException("Ikke laget ennå!");
  }

  private class DobbeltLenketListeIterator implements Iterator<T>
  {
    private Node<T> denne;
    private boolean fjernOK;
    private int iteratorendringer;

    private DobbeltLenketListeIterator()
    {
      denne = hode;     // denne starter på den første i listen
      fjernOK = false;  // blir sann når next() kalles
      iteratorendringer = endringer;  // teller endringer
    }

    private DobbeltLenketListeIterator(int indeks)
    {
      throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public boolean hasNext()
    {
      return denne != null;  // denne koden skal ikke endres!
    }

    @Override
    public T next()
    {
      throw new UnsupportedOperationException("Ikke laget ennå!");
    }

    @Override
    public void remove()
    {
      throw new UnsupportedOperationException("Ikke laget ennå!");
    }

  } // DobbeltLenketListeIterator

} // DobbeltLenketListe
