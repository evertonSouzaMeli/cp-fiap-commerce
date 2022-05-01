package br.com.fiap.entity;

import br.com.fiap.enums.CartStatus;
import lombok.*;
import org.apache.commons.io.FileUtils;

import javax.persistence.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "TB_INVOICE")
@SequenceGenerator(name = "invoice", sequenceName = "SQ_TB_INVOICE", allocationSize = 1)
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice")
    private Integer id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "fl_data", columnDefinition = "BLOB")
    private byte[] data;

    public Invoice(byte[] data){
        this.data = data;
    }


    public static File createInvoiceFile(Cart cart) throws IOException {
        if(cart.getStatus().equals(CartStatus.OPEN)) {
            File file = new File(cart.getBuyer().getName().toLowerCase() + "_invoice_" + LocalDateTime.now() + ".txt");

            FileWriter fileWriter = new FileWriter(file);

            List<Product> products = cart.getProductList();

            fileWriter.write("Buyer: " + cart.getBuyer().getName());
            fileWriter.write("\nDate: " + LocalDateTime.now() + "\n");

            products.forEach(product -> {
                try {
                    fileWriter.write("\nProduto: " + product.getName() + " R$" + product.getPrice());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            fileWriter.write("\n\nTotal: " + "R$" + products.stream().mapToDouble(product -> product.getPrice().doubleValue()).sum());
            fileWriter.close();

            return file;
        }
        return null;
    }

    public static void convertByteToFile(byte[] bytes) throws IOException {
        FileUtils.writeByteArrayToFile(new File("orcl_db_file_invoice_" + LocalDateTime.now() +".txt"), bytes);
    }

    public static byte[] convertFileToByte(File file) throws IOException {
       return FileUtils.readFileToByteArray(file);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
