import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringJoiner;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, hoyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            hoyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }


  public boolean leggInn(T verdi) // en del kodien er kopiert fra kompendiet som var bedt i oppgaven
  {
      Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

      Node<T> p = rot, q = null;               // p starter i roten
      int cmp = 0;                             // hjelpevariabel

      while (p != null)       // fortsetter til p er ute av treet
      {
        q = p;                                 // q er forelder til p
        cmp = comp.compare(verdi,p.verdi);     // bruker komparatoren
        p = cmp < 0 ? p.venstre : p.hoyre;     // flytter p
      }

      // p er nå null, dvs. ute av treet, q er den siste vi passerte

      p = new Node<>(verdi,q);                   // oppretter en ny node

      if (q == null) rot = p;                  // p blir rotnode
      else if (cmp < 0) q.venstre = p;         // venstre barn til q
      else q.hoyre = p;                        // høyre barn til q

      antall++;
      endringer++;                      // én verdi mer i treet
      return true;                             // vellykket innlegging
  }


  public boolean inneholder(T verdi)
  {
    if (verdi == null) return false;

    Node<T> p = rot;

    while (p != null)
    {
      int cmp = comp.compare(verdi, p.verdi);
      if (cmp < 0) p = p.venstre;
      else if (cmp > 0) p = p.hoyre;
      else return true;
    }

    return false;
  }


  public boolean fjern(T verdi) // en del koden er kopiert fra kompendium
  {

      if (verdi == null) return false;  // treet har ingen nullverdier

      Node<T> p = rot, q = null;   // q skal være forelder til p

      while (p != null)            // leter etter verdi
      {
        int cmp = comp.compare(verdi,p.verdi);      // sammenligner
        if (cmp < 0) { q = p; p = p.venstre; }      // går til venstre
        else if (cmp > 0) { q = p; p = p.hoyre; }   // går til høyre
        else break;    // den søkte verdien ligger i p
      }
      if (p == null) return false;   // finner ikke verdi

      if (p.venstre == null || p.hoyre == null)  // Tilfelle 1) og 2)
      {
        Node<T> b = p.venstre != null ? p.venstre : p.hoyre;  // b for barn
        if (p == rot){
            rot = b;
            if(b!= null){
                b.forelder = q;
            }
        }

        else if (p == q.venstre){
            q.venstre = b;
            if(b!= null){
                b.forelder = q;
            }
         }else{
            q.hoyre = b;
            if(b!=null){
                b.forelder = q;
            }
        }


        // else q.hoyre = b;
      }
      else  // Tilfelle 3)
      {
        Node<T> s = p, r = p.hoyre;
        while (r.venstre != null)
        {
          s = r;    // s er forelder til r
          r = r.venstre;
        }

         p.verdi = r.verdi;   // kopierer verdien i r til p

        if(r.hoyre != null){
            r.hoyre.forelder = s;
        }

         if (s != p){
             s.venstre = r.hoyre;
         }
         else{
              s.hoyre = r.hoyre;

         }
          // if (r.hoyre != null) r.hoyre.forelder = s;







      }

      antall--;   // det er nå én node mindre i treet
      endringer++;
      return true;
  }

  public int fjernAlle(T verdi)

  {
    if (tom()){
       return 0;
    }
    int fjern = 0;

    while(inneholder(verdi)) {
        fjern(verdi);
        fjern++;
    }
    return fjern;





  }

  public int antall()
  {
    return antall;
  }

  public int antall(T verdi)

  {
    int ant_forekomster = 0;
    if (verdi == null){ // hvis verdien ikke er i tabell
        return ant_forekomster;
    }
    if(antall()== 0){
        return 0;
    }

    Node<T>temp = rot;
    while(temp!= null){
          int cmp = comp.compare(verdi, temp.verdi); // vi sammenligner  verdien og forelder noden
          if(cmp > 0){
              temp = temp.hoyre;
          }
          // temp = cmp < 0 ? temp.venstre : temp.hoyre; // her sammenligner vi i hvilken subtre av forelder node skal vi sjekke verdien basert på om størrelsen til verdien
          else if(cmp < 0){
              temp = temp.venstre;
          }
          else{
              ant_forekomster++;
              temp = temp.hoyre;
          }

    }
    return ant_forekomster;

  }

  public String toStringPostOrder() {
       if (tom()) return "[]";

       StringJoiner s = new StringJoiner(", ", "[", "]");

       Node<T> p = forstePostorden(rot); // går til den første i postorden
       while (p != null) {
           s.add(p.verdi.toString());
           p = nestePostorden(p);
       }

       return s.toString();
   }


  public boolean tom()
  {
    return antall == 0;
  }

  private  void nullstill(Node<T>p){

    if(tom()){
        return;
    }

    if (p.venstre != null){
         nullstill(p.venstre);
         // p.forelder = null;
         p.venstre = null;

    }

    if (p.hoyre != null){
         nullstill(p.hoyre);
         // p.forelder = null;
         p.hoyre = null;


    }

    p.forelder = null;
    p.verdi = null;


  }


  public void nullstill()
    {
        if (!tom()) {
            Node<T> p = rot;
            nullstill(rot);
            rot = null;
            p = null;
            antall = 0;
            endringer++;


        }
  }

  private static  <T> Node<T> forstePostorden(Node<T> p) {


      while (true)
      {
        if (p.venstre != null){

            p = p.venstre;

        }
        else if (p.hoyre != null){
            p = p.hoyre;

        }
        else break;
      }
      return p;

  }

  private static <T> Node<T> nestePostorden(Node<T> p) {
       Node<T> q = p.forelder;

       if(q == null){
           return null;
       }


       else if(q.hoyre== null || q.hoyre == p){
           return q;

       }

       else return forstePostorden(p.forelder.hoyre); // p.forelder.hoyre






  }



  public void postorden(Oppgave<? super T> oppgave) {
      if(tom()){
          return ;
      }
      Node<T> p = forstePostorden(rot); // forste noden i postorden
      // oppgave.utforOppgave(p.verdi);

      System.out.println(p);

      while(p != null){
          oppgave.utforOppgave(p.verdi);
          p = nestePostorden(p);
          System.out.println(p);

      }



  }
   private void postordenRecursive(Node<T> p,Oppgave<? super T> oppgave){

       if(rot == null){
           return;
       }

       if(p != null){

          postordenRecursive(p.venstre,oppgave);
          postordenRecursive(p.hoyre,oppgave);
          oppgave.utforOppgave(p.verdi);
          System.out.println(p);


       }

   }






   public void postordenRecursive(Oppgave<? super T> oppgave) {

        postordenRecursive(rot,oppgave);

   }

    public ArrayList<T> serialize() {
        ArrayList<T> values = new ArrayList<>();


        if(rot == null){
            return null;
        }

        Queue<Node> queue = new LinkedList<>(); // lager en kø
        queue.add(rot); // vi legger til første noden i kø
        // values.add(rot.verdi);

        while(!queue.isEmpty()){ // så lenge kø er ikke tom
            Node<T> p = queue.poll(); // vi tar ut noden starter med første noden som er lagd inni køen" FIFO"
            values.add(p.verdi); // vi legger verdien til den noden en array list som skal returneres på slutten

            if(p.venstre != null){ // vi sjekker om nodens sin venstre barn ikke er tom
                queue.add(p.venstre); // hviss ikke er tom så legger vi verdien til den noden og går helt til neste noden i samme subtre til vi går ut av treet
            }
            if(p.hoyre != null){// vi sjekker om nodens sin hoyre barn ikke er tom
                queue.add(p.hoyre);// hviss ikke er tom så legger vi verdien til den noden og går helt til neste noden i samme subtre til vi går ut av treet
            }

        }
        return values;




    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {


       SBinTre<K> tre2 = new SBinTre<>(c);




       for(K value: data){
           tre2.leggInn(value);
       }
       return tre2;






   }






// BladnodeIterator

} // ObligSBinTre
