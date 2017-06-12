/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr4.green.s2.core.n1150657.extensions;

import csheets.ext.Extension;
import csheets.ext.deptree.DependencyTreeExtension;
import csheets.ext.simple.ExtensionExample;
import csheets.ext.style.StyleExtension;
import lapr4.blue.s1.lang.n1151031.formulastools.ConditionalStyleExtension;
import lapr4.blue.s1.lang.n1151159.macros.MacroExtension;
import lapr4.blue.s2.ipc.n1060503.chat.ui.ChatParticipantsExtension;
import lapr4.blue.s2.ipc.n1151031.searchnetwork.SearchWorkbookNetworkExtension;
import lapr4.blue.s2.ipc.n1151088.advancedWorkbookSearch.ext.ExtensionFindWorkbook;
import lapr4.blue.s2.ipc.n1151452.netanalyzer.NetAnalyzerExtension;
import lapr4.green.s1.ipc.n1150532.comm.CommExtension;
import lapr4.green.s1.ipc.n1150657.chat.ext.ChatExtension;
import lapr4.green.s1.ipc.n1150800.importexportTXT.ui.ImportExportExtension;
import lapr4.green.s1.ipc.n1150838.findworkbooks.ext.ExtensionFindWorkbooks;
import lapr4.green.s1.ipc.n1150901.search.workbook.SearchWorkbookExtension;
import lapr4.green.s2.core.n1150838.GlobalSearch.GlobalSearchExtension;
import lapr4.green.s2.core.n1150901.richCommentsAndHistory.domain.CommentsWithHistoryExtension;
import lapr4.red.s1.core.n1150613.workbookSearch.SearchExtension;
import lapr4.white.s1.core.n1234567.comments.CommentsExtension;
import lapr4.white.s1.core.n4567890.contacts.ContactsExtension;

/**
 * It represents a factory of extensions. This factory has two criterias for the
 * search.
 *
 * @author Sofia Gonçalves (1150657)
 */
public class ExtensionFactory {

    /**
     * The first version.
     */
    private static final int version1 = 1;

    /**
     * The second version.
     */
    private static final int version2 = 2;

    /**
     * The IllegalArgumentException message.
     */
    private static final String EX_MESSAGE = "(Factory) Extension doesn't exist.";

    /**
     * The static method that will get the pretended extension. It will have two
     * search criterias. The first is about the name of the extension, and the
     * second about the version.
     *
     * @param criteria1 The name of the extension.
     * @param criteria2 the version of the extension.
     * @return It will return the respective class which extends the abstract
     * class Extension. If it doesn't exist, a throw new
     * IllegalArgumentException is throw with the respective message.
     */
    public static Extension getExtension(String criteria1, int criteria2) {
        if (criteria1.equals(ChatExtension.CHAT_NAME)) {
            //Versions
            if (criteria2 == version1) {
                return new ChatExtension();
            }
            if (criteria1.equals(ChatParticipantsExtension.CHAT_NAME)) {
                if (criteria2 == version1) {
                    return new ChatParticipantsExtension();
                }
            }
        } else if (criteria1.equals(StyleExtension.NAME)) {
            if (criteria2 == version1) {
                return new StyleExtension();
            }
        } else if (criteria1.equals(DependencyTreeExtension.NAME)) {
            if (criteria2 == version1) {
                return new DependencyTreeExtension();
            }
        } else if (criteria1.equals(ExtensionExample.NAME)) {
            if (criteria2 == version1) {
                return new ExtensionExample();
            }
        } else if (criteria1.equals(CommentsExtension.NAME)) {
            if (criteria2 == version1) {
                return new CommentsExtension();
            } else if (criteria2 == version2) {
                return new CommentsWithHistoryExtension();
            }
        } else if (criteria1.equals(ContactsExtension.NAME)) {
            if (criteria2 == version1) {
                return new ContactsExtension();
            }
        } else if (criteria1.equals(ExtensionFindWorkbooks.NAME)) {
            if (criteria2 == version1) {
                return new ExtensionFindWorkbooks();
            } else if (criteria2 == version2) {
                return new ExtensionFindWorkbook();
            }
        } else if (criteria1.equals(SearchWorkbookExtension.NAME)) {
            if (criteria2 == version1) {
                return new SearchWorkbookExtension();
            } else if (criteria2 == version2) {
                return new SearchWorkbookNetworkExtension();
            }
        } else if (criteria1.equals(ImportExportExtension.NAME)) {
            if (criteria2 == version1) {
                return new ImportExportExtension();
            }
        } else if (criteria1.equals(MacroExtension.NAME)) {
            if (criteria2 == version1) {
                return new MacroExtension();
            }
        } else if (criteria1.equals(CommExtension.NAME)) {
            if (criteria2 == version1) {
                return new CommExtension();
            }//put the other one
        } else if (criteria1.equals(SearchExtension.NAME)) {
            if (criteria2 == version1) {
                return new SearchExtension();
            } else if (criteria2 == version2) {
                return new GlobalSearchExtension();
            }
        } else if (criteria1.equals(ConditionalStyleExtension.NAME)) {
            if (criteria2 == version1) {
                //return new ConditionalStyleExtension(uiControlle);
            }
        } else if (criteria1.equals(NetAnalyzerExtension.NAME)) {
            if (criteria2 == version1) {
                return new NetAnalyzerExtension();
            }
        }

        throw new IllegalArgumentException(EX_MESSAGE);
    }
}
