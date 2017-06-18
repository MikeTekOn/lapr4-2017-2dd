/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s3.lang.n1150657.XML.Export;

/**
 *
 * @author Sofia
 */
public enum TreeOrder {
    ROOT{
        @Override
        public ElementCleansheet nameRoot(){
            return ElementCleansheet.WORKBOOK;
        }
    },
    ELEMENT_ROOT1{
        @Override
        public ElementCleansheet nameRoot(){
            return ElementCleansheet.SPREADSHEET;
        }
    },
    SUB_ELEMENT_ROOT1{
        @Override
        public ElementCleansheet nameRoot(){
            return ElementCleansheet.CELL;
        }
    },
    SUB_ELEMENT_MACRO{
        @Override
        public ElementCleansheet nameRoot(){
            return ElementCleansheet.MACRO;
        }
    },
    SUB_ELEMENT_COMMENT{
        @Override
        public ElementCleansheet nameRoot(){
            return ElementCleansheet.COMMENT;
        }
    },
    SUB_ELEMENT_STYLE{
        @Override
        public ElementCleansheet nameRoot(){
            return ElementCleansheet.STYLE_FORMATING;
        }
    },
    SUB_ELEMENT_GLOBAL{
        @Override
        public ElementCleansheet nameRoot(){
            return ElementCleansheet.GLOBAL_VARIABLE;
        }
    },
    SUB_ELEMENT_CONTENT{
        @Override
        public ElementCleansheet nameRoot(){
            return ElementCleansheet.CONTENT;
        }
    },
    SUB_ELEMENT_USER{
        @Override
        public ElementCleansheet nameRoot(){
            return ElementCleansheet.USER;
        }
    },
    SUB_ELEMENT_TEXT{
        @Override
        public ElementCleansheet nameRoot(){
            return ElementCleansheet.TEXT;
        }
    };
    public abstract ElementCleansheet nameRoot();
}
