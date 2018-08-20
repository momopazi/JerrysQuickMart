package quickmart;
/**
 *
 * @author LaPaz
 */
public class Lista {

    private Nodo inicio;
    private Nodo fin;

    public Lista() {
        inicio = null;
        fin = null;
    }

    public void ingresarInicio(Product obj) {
        Nodo nuevo = new Nodo(obj, inicio);
        inicio = nuevo;
        if (fin == null) {
            fin = nuevo;
        }
    }

    public void ingresarFin(Product obj) {
        Nodo nuevo = new Nodo(obj, null);
        if (inicio == null) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    public String[][] imprimir() {
        int cont = 1;
        Nodo aux = inicio;
        String printM[][] = new String[100][4];
        printM[0][0] = "ITEM";
        printM[0][1] = "QUANTITY";
        printM[0][2] = "UNIT PRICE";
        printM[0][3] = "TOTAL";
        while (aux != null) {
            printM[cont][0] = aux.getProdu().getItem();
            printM[cont][1] = String.valueOf(aux.getProdu().getQuantity());
            printM[cont][2] = String.valueOf(aux.getProdu().getMemberprice());
            printM[cont][3] = String.valueOf(aux.getProdu().getMemberprice() * aux.getProdu().getQuantity());
            aux = aux.getSiguiente();
            cont++;
        }
        return printM;
    }

    public Product buscar(String item) {
        Nodo aux = inicio;

        while (aux != null) {

            if (aux.getProdu().getItem().equals(item)) {
                return aux.getProdu();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    public boolean buyProduct(String item, int q) {
        Nodo aux = inicio;
        while (aux != null) {
            try {
                //I start with a try because any mathematical operation can cause an exception and this prevents the program from crashing
                //The following 'if' checks if there are enough items on storage before changing it's number
                if (aux.getProdu().getItem().equals(item)) {
                    int n = aux.getProdu().getQuantity() - q;
                    if (n >= 0) {
                        aux.getProdu().setQuantity(n);
                        System.out.print(aux.getProdu().toString());
                        //If there are not enough items on storage, this 'else' tells the user the transaction can't be made
                        //If there is enough on storage, a true value wull be returned
                        return true;
                    } else {
                        System.out.print("\n" + "There is not enough " + item + " on storage for this transaction.");
                        return false;
                        //If the sale was not completed, a false value is returned
                    }
                }
            } //This catch will avoid the program from crashing and alert the user there is something wrong with his procedures
            catch (Exception e) {
                System.out.print("Something went wrong, please retry the transaction." + "\n" + "ERROR: " + e);
                return false;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    int totalItems() {
        Nodo aux = inicio;
        int r = 0;
        while (aux != null) {
            r = r + aux.getProdu().getQuantity();
            aux = aux.getSiguiente();
        }

        return r;
    }

    float totalMoney() {
        Nodo aux = inicio;
        float r = 0;
        while (aux != null) {
            r = r + aux.getProdu().getMemberprice() * aux.getProdu().getQuantity();
            aux = aux.getSiguiente();
        }

        return r;
    }

    float totalSaved() {
        Nodo aux = inicio;
        float r = 0;
        while (aux != null) {
            r = r + aux.getProdu().getRegularPrice() * aux.getProdu().getQuantity();
            aux = aux.getSiguiente();
        }

        return r;
    }

    float Taxation() {
        Nodo aux = inicio;
        float r = 0;
        while (aux != null) {
            if ("Taxable".equals(aux.getProdu().getTaxStatus())) {
                r = r + aux.getProdu().getMemberprice() * aux.getProdu().getQuantity();
            }
            aux = aux.getSiguiente();
        }
        r = (float) (r * 0.065);
        return r;
    }

    void clearAll() {
        Nodo aux = inicio;
        while (aux != null) {
            aux.getProdu().setItem(null);
            aux.getProdu().setMemberprice(0);
            aux.getProdu().setQuantity(0);
            aux.getProdu().setRegularPrice(0);
            aux.getProdu().setTaxStatus(null);
            aux = aux.getSiguiente();
        }
    }

}
