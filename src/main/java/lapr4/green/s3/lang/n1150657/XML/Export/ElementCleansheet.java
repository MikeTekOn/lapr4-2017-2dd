/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Export;

/**
 * Enum that contains the persisting lements of the cleansheet.
 *
 * @author Sofia Gonçalves (1150657)
 */
public enum ElementCleansheet {
    WORKBOOK{
        @Override
        public String getElementName(){
            return "workbook";
        }
    }, 
    SPREADSHEET{
        @Override
        public String getElementName(){
            return "spreadsheet";
        }
    },
    CELL{
        @Override
        public String getElementName(){
            return "cell";
        }
    },
    CONTENT{
        @Override
        public String getElementName(){
            return "content";
        }
    },
    MACRO{
        @Override
        public String getElementName(){
            return "macro";
        }
    },
    COMMENT{
        @Override
        public String getElementName(){
            return "comment";
        }
    },
    GLOBAL_VARIABLE{
        @Override
        public String getElementName(){
            return "global_variable";
        }
    },
    FONT{
        @Override
        public String getElementName(){
            return "font";
        }
    },
    FOREGROUNG{
        @Override
        public String getElementName(){
            return "foreground";
        }
    };
    public abstract String getElementName();
    public static int sizeOf = ElementCleansheet.values().length;
}
