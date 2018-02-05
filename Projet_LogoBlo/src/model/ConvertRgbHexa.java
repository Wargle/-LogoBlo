public class ConvertRgbHexa {
    private static String[][] mapColors;

    public ConvertRgbHexa() {
        mapColors = new String[4][4];
        String[] hexValues = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        int i = 0, j = 0;
        for (String h: hexValues) {
            mapColors[i][j] = h;
            if(j == 3){
                j = 0;
                i++;
            }
            else
                j++;
        }

        for(int ii = 0; ii < 4; ii++){
            for(int jj = 0; jj < 4; jj++)
                System.out.print(mapColors[ii][jj]);
            System.out.println();
        }
    }

    public static String getHexaFromRG(int r, int g) {
        int i = r/64, j = g/64;
        return mapColors[i][j];
    }
}
