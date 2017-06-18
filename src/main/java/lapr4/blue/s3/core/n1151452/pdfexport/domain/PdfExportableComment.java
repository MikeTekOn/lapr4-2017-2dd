package lapr4.blue.s3.core.n1151452.pdfexport.domain;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import eapli.framework.domain.ValueObject;
import lapr4.red.s1.core.n1150690.comments.CommentableCellWithMultipleUsers;
import lapr4.red.s1.core.n1150690.comments.domain.User;

import java.util.List;
import java.util.Map;

/**
 * A PDF exportable comment
 *
 * @author Daniel Gonçalves [1151452@isep.ipp.pt]
 *         on 16/06/17.
 */
public class PdfExportableComment implements ValueObject {

    /**
     * The related commentable cell
     */
    private final CommentableCellWithMultipleUsers cell;

    /**
     * Constructs a PDF Exportable Comment.
     *
     * @param commentableCell the related commentable cell
     */
    public PdfExportableComment(CommentableCellWithMultipleUsers commentableCell) {

        if (commentableCell == null) throw new IllegalStateException("Commentable Cell can't be null.");

        cell = commentableCell;
    }

    /**
     * Transforms the comment in a PDF Paragraph element
     *
     * @return the comment in a PDF Paragraph element
     */
    public Paragraph exportComment() {

        Paragraph pdfComments = new Paragraph();
        Map<User, List<String>> userComments = cell.comments();
        userComments.forEach((user, comments) -> {

            Paragraph username = new Paragraph(user.name());
            Font usernameFont = username.getFont();
            usernameFont.setStyle(Font.BOLD);
            username.setFont(usernameFont);
            username.setSpacingAfter(1.5f);

            Paragraph pdfUserComments = new Paragraph(username);

            comments.forEach((comment) -> {

                Paragraph pdfComment = new Paragraph(comment);
                pdfComment.setSpacingAfter(1.0f);

                pdfComments.add(pdfComment);
            });

            pdfComments.add(pdfUserComments);
        });
        return pdfComments;
    }

    /**
     * Transforms the comment's history in a PDF Paragraph element
     *
     * @return the comment's history in a PDF Paragraph element
     */
    public Paragraph exportHistory() {

        // TODO: REVIEW
        return new Paragraph(cell.getCommentHistory());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PdfExportableComment that = (PdfExportableComment) o;

        return cell != null ? cell.equals(that.cell) : that.cell == null;
    }

    @Override
    public int hashCode() {
        return cell != null ? cell.hashCode() : 0;
    }
}
