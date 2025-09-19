// https://leetcode.com/problems/design-spreadsheet?envType=daily-question&envId=2025-09-19

class Spreadsheet {
    private int[][] Cells;

    public Spreadsheet(int rows) {
        Cells = new int[rows + 1][26];
    }

    private int getNumber(String s) {
        int value = 0;
        for (int i = 0; i < s.length(); ++i) {
            int digit = s.charAt(i) - '0';
            value = value * 10 + digit;
        }
        return value;
    }

    public void setCell(String cell, int value) {
        int column = cell.charAt(0) - 'A';
        int row = getNumber( cell.substring(1) );
        Cells[row][column] = value;
    }

    public void resetCell(String cell) {
        setCell(cell, 0);
    }

    private boolean isCharacter(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    private int getTermValue(String x) {
        if ( isCharacter( x.charAt(0) ) ) {
            return Cells[ getNumber( x.substring(1) ) ][ x.charAt(0) - 'A' ];
        }
        return getNumber(x);
    }

    public int getValue(String formula) {
        int p = -1;
        for (int i = 0; i < formula.length(); ++i) {
            if ( formula.charAt(i) == '+' ) {
                p = i;
                break;
            }
        }
        return getTermValue( formula.substring(1, p) ) + getTermValue( formula.substring(p + 1) );
    }
}

/**
 * Your Spreadsheet object will be instantiated and called as such:
 * Spreadsheet obj = new Spreadsheet(rows);
 * obj.setCell(cell,value);
 * obj.resetCell(cell);
 * int param_3 = obj.getValue(formula);
 */