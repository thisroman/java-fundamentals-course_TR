package rn.dev;

public class SpaseT {

    public static void main(String[] args) {
        Object[] input = new Object[]{"1", "2", "3", 'x', "5", "6", "a", "porosiatko", "c", "10", 11, "12", "13", "14", "15", new Exception() {
            @Override
            public String toString() {
                return "16";
            }
        }};

        int columnsCount = 7;// default 5
        Table pTable = new Table(input.length, columnsCount);
        for (Object data : input) {
            pTable.insert(String.valueOf(data));
        }
        int space = 2;// default 4
        pTable.print(space);
    }

    private static class Table{
        private final Column[] columns;
        private int insertIndex = 0;
        private static int colCount = 5;

        public Table(int total) {
            this(total, 5);
        }

        public Table(int total, int columnsCount){
            colCount = columnsCount;
            columns = new Column[colCount];
            int length = total/ colCount;

            int plusRow = total% colCount;
            for (int i = 0; i < colCount; i++) {
                if (plusRow > 0) {
                    columns[i] = new Column(length + 1);
                    plusRow--;
                } else {
                    columns[i] = new Column(length);
                }
            }
        }

        public void insert(String data){
            if(columns[insertIndex].isInsertDone()){
                throw new IndexOutOfBoundsException("Table column is full");
            }
            columns[insertIndex].setElement(data);
            insertIndex++;
            if (insertIndex == colCount){
                insertIndex = 0;
            }
        }

        public void print(){
            int columnsDone = 0;
            while (columnsDone < colCount) {
                for (int i = 0; i < colCount; i++) {
                    if (!columns[i].isPrintDone() && !columns[i].printNext()){
                        columnsDone++;
                    }
                }
                System.out.println("");
            }
        }

        public void print(int space){
            int columnsDone = 0;
            while (columnsDone < colCount) {
                for (int i = 0; i < colCount; i++) {
                    if (!columns[i].isPrintDone() && !columns[i].printNext(space)){
                        columnsDone++;
                    }
                }
                System.out.println("");
            }
        }
    }

    private static class Column{
        private final String[] elements;
        private int maxWidth = 0;
        private int currentInsert = 0;
        private int currentPrint = 0;
        private final int len;

        public Column(int length){
            this.len = length;
            this.elements = new String[len];
        }

        public void setElement(String el){
            if (currentInsert >= len){
                throw new IndexOutOfBoundsException("Column is full");
            }
            if (el.length() > maxWidth){
                maxWidth = el.length();
            }
            elements[currentInsert] = el;
            currentInsert++;
        }

        public void print(int pos, int space){
            if (pos >= len){
                throw new IndexOutOfBoundsException("Column print is done");
            }
            String el = elements[pos];
            System.out.print(el + " ".repeat(space + maxWidth - el.length()));
        }

        public boolean printNext(){
            return printNext(4);
        }

        public boolean printNext(int space){
            print(currentPrint, space);
            currentPrint++;
            return currentPrint < len;
        }

        public boolean isInsertDone(){
            return currentInsert >= len;
        }

        public boolean isPrintDone(){
            return currentPrint >= len;
        }

        public void clearInsertPos(){
            currentInsert = 0;
        }

        public void clearPrintPos(){
            currentPrint = 0;
        }
    }
}
