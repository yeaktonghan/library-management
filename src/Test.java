//import org.nocrala.tools.texttablefmt.BorderStyle;
//import org.nocrala.tools.texttablefmt.ShownBorders;
//import org.nocrala.tools.texttablefmt.Table;
//import org.nocrala.tools.texttablefmt.CellStyle;
//
//
//
//public class Test {
//    public static void main(String[] atge){
//        do {
//            CellStyle numberStyle = new CellStyle(HorizontalAlign.center);
//            Table t = new Table(5, BorderStyle.UNICODE_BOX_DOUBLE_BORDER, ShownBorders.ALL);
//            t.setColumnWidth(0, 3, 5);
//            t.setColumnWidth(1, 20, 26);
//            t.setColumnWidth(2, 30, 26);
//            t.setColumnWidth(3, 20, 26);
//            t.setColumnWidth(4, 10, 26);
//            t.addCell("ALL BOOKS INFO ", numberStyle, (5));
//            t.addCell(" ID", numberStyle);
//            t.addCell("TITLE", numberStyle);
//            t.addCell("AUTHOR", numberStyle);
//            t.addCell("PUBLISH DATE", numberStyle);
//            t.addCell("STATUS", numberStyle);
//
//            for (int i = j; i < col; i++) {
//                if (book[i] != null) {
//                    t.addCell(String.valueOf(book[i].getId()), numberStyle);
//                    t.addCell(book[i].getTitle(), numberStyle);
//                    t.addCell(book[i].getAuthor().getAuthor_name() + " (" + book[i].getAuthor().getActive_year() + " )", numberStyle);
//                    t.addCell(book[i].getPublished_year(), numberStyle);
//                    t.addCell(String.valueOf(book[i].getStatus()), numberStyle);
//                } else if (book[i] == null) {
//                    t.addCell("No Record to Show ", numberStyle, (5));
//                    break;
//                }
//            }
//            j = col;
//            System.out.println(t.render());
//            System.out.println("Press key 1 to continues and press key 0 to exit!");
//            key = scanner.nextInt();
//            col = col + col;
//            if (key == 1) {
//                l = true;
//            } else if (key == 0) {
//                l = false;
//            }
//        } while (l);
//    }
//}
