package com.store.dominguez.mock;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.store.dominguez.model.*;
import com.store.dominguez.repository.gestion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MockDataConfig implements CommandLineRunner {

    private final ProductoRepository productoRepository;
    private final TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository;
    private final ProveedorRepository proveedorRepository;
    private final ClienteRepository clienteRepository;
    private final RolRepository rolRepository;
    private final EmpleadoRepository empleadoRepository;
    private final CategoriaRepository categoriaRepository;
    private final TipoTransaccionRepository tipoTransaccionRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MockDataConfig(ProductoRepository productoRepository, ClienteRepository clienteRepository, RolRepository rolRepository, EmpleadoRepository empleadoRepository, CategoriaRepository categoriaRepository, ProveedorRepository proveedorRepository, TipoDocumentoIdentidadRepository tipoDocumentoIdentidadRepository, TipoTransaccionRepository tipoTransaccionRepository, PasswordEncoder passwordEncoder) {
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
        this.rolRepository = rolRepository;
        this.empleadoRepository = empleadoRepository;
        this.categoriaRepository = categoriaRepository;
        this.proveedorRepository = proveedorRepository;
        this.tipoDocumentoIdentidadRepository = tipoDocumentoIdentidadRepository;
        this.tipoTransaccionRepository = tipoTransaccionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (productoRepository.count() == 0 || categoriaRepository.count() == 0) {
            List<CategoriaEntity> categorias = initCategorias();
            List<ProductoEntity> productos = initProducts(categorias);

            for (CategoriaEntity categoria : categorias) {
                categoriaRepository.save(categoria);
            }

            for (ProductoEntity producto : productos) {
                productoRepository.save(producto);
            }
        }


        if (rolRepository.count() == 0) {
            List<RolEntity> roles = initRoles();
            for (RolEntity rol : roles) {
                rolRepository.save(rol);
            }
        }

        if (tipoDocumentoIdentidadRepository.count() == 0) {
            List<TipoDocumentoIdentidadEntity> tipoDocumentos = initTipoDocumentos();
            for (TipoDocumentoIdentidadEntity tipoDocumento : tipoDocumentos) {
                tipoDocumentoIdentidadRepository.save(tipoDocumento);
            }
        }

        if (clienteRepository.count() == 0) {
            List<TipoDocumentoIdentidadEntity> tipoDocumentos = tipoDocumentoIdentidadRepository.findAll();
            List<RolEntity> roles = rolRepository.findAll();
            List<ClienteEntity> clientes = initClients(roles, tipoDocumentos);
            for (ClienteEntity cliente : clientes) {
                clienteRepository.save(cliente);
            }
        }

        if (empleadoRepository.count() == 0) {
            List<TipoDocumentoIdentidadEntity> tipoDocumentos = tipoDocumentoIdentidadRepository.findAll();
            List<RolEntity> roles = rolRepository.findAll();
            List<EmpleadoEntity> empleados = initEmpleados(roles, tipoDocumentos);
            for (EmpleadoEntity empleado : empleados) {
                empleadoRepository.save(empleado);
            }
        }

        if (proveedorRepository.count() == 0) {
            List<TipoDocumentoIdentidadEntity> tipoDocumentos = initTipoDocumentos();
            List<ProveedorEntity> proveedores = initProveedores(tipoDocumentos);
            for (ProveedorEntity proveedor : proveedores) {
                proveedorRepository.save(proveedor);
            }
        } else {
            System.out.println("La base de datos ya contiene los datos.");
        }

        if (tipoTransaccionRepository.count() == 0) {
            List<TipoTransaccionEntity> tipoTransacciones = initTransacciones();
            for (TipoTransaccionEntity tipoTransaccion : tipoTransacciones) {
                tipoTransaccionRepository.save(tipoTransaccion);
            }
        } else {
            System.out.println("La base de datos ya contiene los datos.");
        }

    }

    /*
     * Inicialización de la creación de las entidades
     * */

    public static List<CategoriaEntity> initCategorias() {
        List<CategoriaEntity> categorias = new ArrayList<>();
        categorias.add(new CategoriaEntity("CAT-POC-AS120K", "Procesadores", "Unidad central de procesamiento (CPU) que ejecuta las instrucciones de un programa y realiza cálculos.", true, "https://i.blogs.es/9862e7/intel/450_1000.jpeg"));
        categorias.add(new CategoriaEntity("CAT-TJG-ZX321F", "Tarjetas gráficas", "Componente que procesa y genera imágenes para ser mostradas en la pantalla.", true, "https://sc04.alicdn.com/kf/H6e3a33d7fde242709f5803ed816ac8fbD.jpg"));
        categorias.add(new CategoriaEntity("CAT-PAC-YH874B", "Placas base", "Placa de circuito impreso que conecta todos los componentes de hardware de una computadora.", true, "https://xanatos.es/wp-content/uploads/2021/05/asus-rog-maximus-xii-extreme-1200-d.jpg"));
        categorias.add(new CategoriaEntity("CAT-AMA-ALM421", "Almacenamiento", "Categoría que engloba dispositivos utilizados para guardar y gestionar datos de manera permanente o temporal en sistemas informáticos. Incluye dispositivos como discos duros, unidades de estado sólido (SSD), tarjetas de memoria, entre otros.", true, "https://newsbook.es/wp-content/uploads/2013/09/1foto11406.jpg"));
        categorias.add(new CategoriaEntity("CAT-AUD-ADI921", "Audifonos", "Dispositivo de audio que se colocan sobre las orejas para escuchar sonidos provenientes de una computadora u otro dispositivo electrónico.", true, "https://http2.mlstatic.com/D_NQ_NP_760857-MLA44771394445_022021-O.webp"));
        categorias.add(new CategoriaEntity("CAT-MON-HG432Z", "Monitores", "Pantalla de visualización utilizada para mostrar imágenes generadas por la computadora.", true, "https://consumer.huawei.com/content/dam/huawei-cbg-site/latam/latin/mkt/plp/monitors/mateview-gt-series/mateview-gt-series-1.jpg"));
        categorias.add(new CategoriaEntity("CAT-COO-BC123W", "Coolers de Refrigeración", "Dispositivo de hardware diseñado para disipar el calor generado por componentes internos de una computadora, como el procesador (CPU) o la tarjeta gráfica (GPU), manteniendo así una temperatura óptima de funcionamiento.", true, "https://http2.mlstatic.com/D_NQ_NP_601302-MLM48393265754_112021-O.webp"));
        categorias.add(new CategoriaEntity("CAT-PSU-VN876P", "Fuentes de alimentación", "Componente que suministra energía eléctrica a los componentes de una computadora.", true, "https://m.media-amazon.com/images/I/41CeZQLDl-S._SL500_.jpg"));
        categorias.add(new CategoriaEntity("CAT-CAE-LM435R", "Cases", "Estructura que aloja y protege los componentes de una computadora.", true, "https://mipclista.com/3173-large_default/case-gamer-1st-player-zx7.jpg"));
        categorias.add(new CategoriaEntity("CAT-SLL-PL678E", "Sillas Gamer", "Sillas ergonómicas diseñadas para proporcionar comodidad y soporte durante largas. Estas sillas suelen tienen la características de soporte lumbar ajustable, reposabrazos acolchados y reclinación ajustable para una experiencia de juego cómoda..", true, "https://thumb.pccomponentes.com/w-530-530/articles/18/180612/1.jpg"));

        return categorias;
    }

    public List<ProductoEntity> initProducts(List<CategoriaEntity> categorias) {
        List<ProductoEntity> productos = new ArrayList<>();

        JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode fichaTecnica = factory.objectNode();

        /*PROCESADORES*/
        productos.add(crearProducto("PROD-ITC-1K1001", "Intel Core i9-11900K", "Procesador de última generación para computadoras de alto rendimiento.", BigDecimal.valueOf(499.99), "Intel", 20, "https://www.hbltecstore.pe/cdn/shop/files/71Qc91x5b8L._AC_SX679.jpg?v=1683148042", true, findCategoriaById(categorias, "CAT-POC-AS120K"), fichaTecnica.put("frecuencia", "3.5 GHz").put("nucleos", "8").put("hilos", "16")));
        productos.add(crearProducto("PROD-ATC-2K1002", "AMD Ryzen 9 5950X", "Potente procesador de la serie Ryzen de AMD, ideal para aplicaciones exigentes y juegos de alta gama.", BigDecimal.valueOf(799.99), "AMD", 15, "https://pcexpress.pe/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/c/p/cpam4r95950x.jpg", true, findCategoriaById(categorias, "CAT-POC-AS120K"), fichaTecnica.put("frecuencia", "3.4 GHz").put("nucleos", "16").put("hilos", "32")));
        productos.add(crearProducto("PROD-OTC-3K1003", "Intel Core i7-11700K", "Procesador de alto rendimiento de la 11ª generación de Intel, adecuado para gaming y aplicaciones de productividad.", BigDecimal.valueOf(399.99), "Intel", 25, "https://store.teslards.pe/wp-content/uploads/2021/03/1880-intel-core-i9-11900k-35-ghz.jpg", true, findCategoriaById(categorias, "CAT-POC-AS120K"), fichaTecnica.put("frecuencia", "3.6 GHz").put("nucleos", "8").put("hilos", "16")));
        productos.add(crearProducto("PROD-DTC-4K1004", "AMD Ryzen 7 5800X", "Procesador de la serie Ryzen de AMD, ofrece un excelente rendimiento en gaming y tareas multitarea intensivas.", BigDecimal.valueOf(449.99), "AMD", 30, "https://http2.mlstatic.com/D_NQ_NP_921861-MLA51718918703_092022-O.webp", true, findCategoriaById(categorias, "CAT-POC-AS120K"), fichaTecnica.put("frecuencia", "3.8 GHz").put("nucleos", "8").put("hilos", "16")));
        productos.add(crearProducto("PROD-QTC-5K1005", "Intel Core i5-11600K", "Procesador de la 11ª generación de Intel, ofrece un excelente equilibrio entre rendimiento y precio para gaming y tareas diarias.", BigDecimal.valueOf(269.99), "Intel", 40, "https://cyccomputer.pe/34230-medium_default/procesador-intel-core-i5-11600k-390ghz12mb-lga-1200-pnbx8070811600k.jpg", true, findCategoriaById(categorias, "CAT-POC-AS120K"), fichaTecnica.put("frecuencia", "3.9 GHz").put("nucleos", "6").put("hilos", "12")));

        /* Tarjetas gráficas */
        productos.add(crearProducto("PROD-NVD-RTX3080", "NVIDIA GeForce RTX 3080 TI", "Potente tarjeta gráfica de la serie RTX 3000 de NVIDIA, diseñada para ofrecer un rendimiento excepcional en juegos y aplicaciones de renderizado.", BigDecimal.valueOf(699.99), "NVIDIA", 10, "https://smartbusiness.pe/cdn/shop/products/81UBILsWwiS._AC_SL1500.jpg?v=1702514253", true, findCategoriaById(categorias, "CAT-TJG-ZX321F"), fichaTecnica.put("memoria", "10 GB GDDR6X").put("nucleosCUDA", "8704").put("frecuenciaReloj", "1.71 GHz")));
        productos.add(crearProducto("PROD-AMD-RX6900XT", "AMD Radeon RX 6900 XT", "Tarjeta gráfica de alta gama de AMD, ofrece un rendimiento excepcional en juegos y tareas de renderizado intensivas.", BigDecimal.valueOf(999.99), "AMD", 8, "https://asset.msi.com/resize/image/global/product/product_1612246958fa75eb32b6ae31058dbd816d78fb3d0e.png62405b38c58fe0f07fcef2367d8a9ba1/1024.png", true, findCategoriaById(categorias, "CAT-TJG-ZX321F"), fichaTecnica.put("memoria", "16 GB GDDR6").put("nucleosStream", "5120").put("frecuenciaReloj", "2.25 GHz")));
        productos.add(crearProducto("PROD-NVD-RTX3070", "NVIDIA GeForce RTX 3070", "Tarjeta gráfica de la serie RTX 3000 de NVIDIA, ofrece un excelente rendimiento en juegos de alta gama y aplicaciones de edición de video.", BigDecimal.valueOf(499.99), "NVIDIA", 15, "https://store.perudataconsult.net/cdn/shop/products/TarjetadeVideoEVGAGeforceRTX3070TIPORTADA.jpg?v=1647987831", true, findCategoriaById(categorias, "CAT-TJG-ZX321F"), fichaTecnica.put("memoria", "8 GB GDDR6").put("nucleosCUDA", "5888").put("frecuenciaReloj", "1.73 GHz")));
        productos.add(crearProducto("PROD-AMD-RX6800", "AMD Radeon RX 6800", "Tarjeta gráfica de la serie RX 6000 de AMD, ofrece un rendimiento excepcional en juegos de alta resolución y aplicaciones de diseño gráfico.", BigDecimal.valueOf(579.99), "AMD", 12, "https://m.media-amazon.com/images/I/81c3PiQLBUL.jpg", true, findCategoriaById(categorias, "CAT-TJG-ZX321F"), fichaTecnica.put("memoria", "16 GB GDDR6").put("nucleosStream", "3840").put("frecuenciaReloj", "1.82 GHz")));
        productos.add(crearProducto("PROD-NVD-GTX1660", "NVIDIA GeForce GTX 1660", "Tarjeta gráfica de la serie GTX 16 de NVIDIA, ofrece un excelente rendimiento en juegos de 1080p y 1440p a un precio asequible.", BigDecimal.valueOf(249.99), "NVIDIA", 20, "https://oechsle.vteximg.com.br/arquivos/ids/15768379-1000-1000/image-13a58032418c44c4a39005fa73e3980b.jpg?v=638280803688700000", true, findCategoriaById(categorias, "CAT-TJG-ZX321F"), fichaTecnica.put("memoria", "6 GB GDDR5").put("nucleosCUDA", "1408").put("frecuenciaReloj", "1.53 GHz")));

        /*Placas base*/
        productos.add(crearProducto("PROD-ASS-Z590", "ASUS ROG Strix Z590-E Gaming", "Placa base de alta gama de la serie ROG Strix de ASUS, diseñada para procesadores Intel de 10ª y 11ª generación, con características avanzadas para gaming y overclocking.", BigDecimal.valueOf(349.99), "ASUS", 15, "https://cyccomputer.pe/33292-large_default/placa-asus-rog-strix-z590-e-gaming-wifi-ddr4-lga-1200-pn90mb1640-m0eayo.jpg", true, findCategoriaById(categorias, "CAT-PAC-YH874B"), fichaTecnica.put("socket", "LGA1200").put("chipset", "Intel Z590").put("formato", "ATX")));
        productos.add(crearProducto("PROD-GIG-B550", "GIGABYTE B550 AORUS Elite", "Placa base de la serie AORUS de GIGABYTE, compatible con procesadores AMD Ryzen de 3ª generación, con características de alta calidad y soporte para PCIe 4.0.", BigDecimal.valueOf(159.99), "GIGABYTE", 20, "https://xercom.com.pe/wp-content/uploads/2021/02/B550-AORUS-ELITE-1.jpg", true, findCategoriaById(categorias, "CAT-PAC-YH874B"), fichaTecnica.put("socket", "AM4").put("chipset", "AMD B550").put("formato", "ATX")));
        productos.add(crearProducto("PROD-MSI-Z490", "MSI MAG Z490 TOMAHAWK", "Placa base de la serie MAG de MSI, compatible con procesadores Intel de 10ª generación, con un diseño robusto y características para gaming.", BigDecimal.valueOf(199.99), "MSI", 25, "https://www.impacto.com.pe/storage/products/1640823732.jpg", true, findCategoriaById(categorias, "CAT-PAC-YH874B"), fichaTecnica.put("socket", "LGA1200").put("chipset", "Intel Z490").put("formato", "ATX")));
        productos.add(crearProducto("PROD-ASR-X570", "ASRock X570 Taichi", "Placa base de la serie Taichi de ASRock, compatible con procesadores AMD Ryzen de 3ª generación, con un diseño elegante y características de alta gama.", BigDecimal.valueOf(299.99), "ASRock", 18, "https://http2.mlstatic.com/D_NQ_NP_909868-MPE69318085184_052023-O.webp", true, findCategoriaById(categorias, "CAT-PAC-YH874B"), fichaTecnica.put("socket", "AM4").put("chipset", "AMD X570").put("formato", "ATX")));
        productos.add(crearProducto("PROD-ROG-Z590", "ROG STRIX Z590 FTW WiFi", "Placa base de la serie FTW de asus, diseñada para procesadores Intel de 10ª y 11ª generación, con un diseño sólido y características de overclocking.", BigDecimal.valueOf(279.99), "ASUS", 12, "https://www.impacto.com.pe/storage/products/md/167302276825895.jpg", true, findCategoriaById(categorias, "CAT-PAC-YH874B"), fichaTecnica.put("socket", "LGA1200").put("chipset", "Intel Z590").put("formato", "ATX")));


        /*Almacenamiento*/
        productos.add(crearProducto("PROD-WSD-1TBHDD", "Disco Solido M.2 Western Digital Blue Sn580 1tb Pcie Nvme", "Útil para guardar programas y documentos con su capacidad de 1 TB.Interfaz de conexión: NVMe.Incrementa el rendimiento de tu equipo.", BigDecimal.valueOf(49.99), "Western Digital", 30, "https://http2.mlstatic.com/D_NQ_NP_763844-MLU72461321556_102023-O.webp", true, findCategoriaById(categorias, "CAT-AMA-ALM421"), fichaTecnica.put("capacidad", "1TB").put("interfaz", "SATA 6 Gb/s").put("velocidad", "7200 RPM")));
        productos.add(crearProducto("PROD-SEA-2TBHDD", "Seagate Barracuda 2TB HDD", "Disco duro interno de 3.5 pulgadas con capacidad de 2TB de la serie Barracuda de Seagate, ofrece un equilibrio entre capacidad, rendimiento y precio para aplicaciones de almacenamiento masivo.", BigDecimal.valueOf(69.99), "Seagate", 25, "https://http2.mlstatic.com/D_NQ_NP_767861-MLA70115526648_062023-O.webp", true, findCategoriaById(categorias, "CAT-AMA-ALM421"), fichaTecnica.put("capacidad", "2TB").put("interfaz", "SATA 6 Gb/s").put("velocidad", "7200 RPM")));
        productos.add(crearProducto("PROD-TSH-500VGB", "Toshiba P300 500GB HDD", "Disco duro interno de 3.5 pulgadas con capacidad de 500GB de la serie P300 de Toshiba, ofrece un almacenamiento confiable para aplicaciones de uso general.", BigDecimal.valueOf(39.99), "Toshiba", 20, "https://http2.mlstatic.com/D_Q_NP_622097-MLA26004565921_092017-O.webp", true, findCategoriaById(categorias, "CAT-AMA-ALM421"), fichaTecnica.put("capacidad", "500GB").put("interfaz", "SATA 6 Gb/s").put("velocidad", "7200 RPM")));
        productos.add(crearProducto("PROD-WTD-4TBHDD", "Western Digital Red 4TB HDD", "Disco duro interno de 3.5 pulgadas con capacidad de 4TB de la serie Red de Western Digital, optimizado para sistemas NAS (Network Attached Storage) para entornos de almacenamiento en red.", BigDecimal.valueOf(129.99), "Western Digital", 15, "https://storage.googleapis.com/catalog-pictures-carrefour-es/catalog/pictures/hd_510x_/0718037855967_1.jpg", true, findCategoriaById(categorias, "CAT-AMA-ALM421"), fichaTecnica.put("capacidad", "4TB").put("interfaz", "SATA 6 Gb/s").put("velocidad", "5400 RPM")));
        productos.add(crearProducto("PROD-SVX-1TBSHD", "Seagate FireCuda 1TB SSHD", "Disco híbrido sólido de 2.5 pulgadas con capacidad de 1TB de la serie FireCuda de Seagate, combina almacenamiento de alta capacidad con rendimiento SSD para una experiencia de juego más rápida.", BigDecimal.valueOf(89.99), "Seagate", 10, "https://m.media-amazon.com/images/I/71wYXvgjyyL._AC_SL1500_.jpg", true, findCategoriaById(categorias, "CAT-AMA-ALM421"), fichaTecnica.put("capacidad", "1TB").put("interfaz", "SATA 6 Gb/s").put("tecnologia", "SSHD")));


        /*Audifonos*/
        productos.add(crearProducto("PROD-RZR-BLACKS", "Razer BlackShark V2", "Audífonos para juegos con sonido envolvente THX Spatial Audio de la serie BlackShark V2 de Razer, diseñados para ofrecer una experiencia de juego inmersiva y cómoda.", BigDecimal.valueOf(99.99), "Razer", 20, "https://promart.vteximg.com.br/arquivos/ids/7413995-1000-1000/image-de617e4422604921856f50ab89b94131.jpg?v=638272155293830000", true, findCategoriaById(categorias, "CAT-AUD-ADI921"), fichaTecnica.put("tipo", "Over-ear").put("conectividad", "Alámbrico").put("compatibilidad", "PC, consolas").put("iluminacionRGB", "Sí")));
        productos.add(crearProducto("PROD-HYX-CLODAL", "HyperX Cloud Alpha", "Audífonos para juegos con controladores de 50 mm de la serie Cloud Alpha de HyperX, ofrecen un sonido envolvente y comodidad duradera para largas sesiones de juego.", BigDecimal.valueOf(89.99), "HyperX", 25, "https://row.hyperx.com/cdn/shop/products/hyperx_cloud_alpha_black_1_main_900x.jpg?v=1662420668", true, findCategoriaById(categorias, "CAT-AUD-ADI921"), fichaTecnica.put("tipo", "Over-ear").put("conectividad", "Alámbrico").put("compatibilidad", "PC, consolas").put("iluminacionRGB", "No")));
        productos.add(crearProducto("PROD-STE-ARCIS7", "SteelSeries Arctis 7", "Audífonos inalámbricos para juegos con audio DTS Headphone:X v2.0 de la serie Arctis 7 de SteelSeries, ofrecen una conexión inalámbrica sin pérdida y comodidad para largas sesiones de juego.", BigDecimal.valueOf(149.99), "SteelSeries", 30, "https://http2.mlstatic.com/D_NQ_NP_821576-MPE72549507937_102023-O.webp", true, findCategoriaById(categorias, "CAT-AUD-ADI921"), fichaTecnica.put("tipo", "Over-ear").put("conectividad", "Inalámbrico").put("compatibilidad", "PC, consolas").put("iluminacionRGB", "Sí")));
        productos.add(crearProducto("PROD-COR-HS60RO", "CORSAIR HS60 Pro", "Audífonos para juegos con sonido envolvente 7.1 de la serie HS60 Pro de CORSAIR, ofrecen una construcción duradera y comodidad para largas sesiones de juego.", BigDecimal.valueOf(69.99), "CORSAIR", 35, "https://dojiw2m9tvv09.cloudfront.net/48881/product/corsair-hs60-pro-7-1-virtual-surround-sound-pc-55590.jpg", true, findCategoriaById(categorias, "CAT-AUD-ADI921"), fichaTecnica.put("tipo", "Over-ear").put("conectividad", "Alámbrico").put("compatibilidad", "PC, consolas").put("iluminacionRGB", "No")));
        productos.add(crearProducto("PROD-LOQ-G73311", "Logitech G733", "Audífonos inalámbricos para juegos con tecnología de micrófono Blue VO!CE de la serie G733 de Logitech, ofrecen un diseño colorido y ligero para una experiencia de juego cómoda y personalizable.", BigDecimal.valueOf(129.99), "Logitech", 40, "https://promart.vteximg.com.br/arquivos/ids/769273-1000-1000/image-12e5169ccc964a4da18813c9045fdb4e.jpg?v=637496878856170000", true, findCategoriaById(categorias, "CAT-AUD-ADI921"), fichaTecnica.put("tipo", "Over-ear").put("conectividad", "Inalámbrico").put("compatibilidad", "PC, consolas").put("iluminacionRGB", "Sí")));


        /*Monitores*/
        productos.add(crearProducto("PROD-AOS-XG279Q", "ASUS ROG Strix XG279Q", "Monitor gaming de 27 pulgadas con resolución WQHD y frecuencia de actualización de 170Hz de la serie ROG Strix de ASUS, diseñado para ofrecer una experiencia de juego suave y envolvente.", BigDecimal.valueOf(499.99), "ASUS", 15, "https://dlcdnwebimgs.asus.com/gain/44B3AED5-B1F6-4012-84F8-F624789BB932/w717/h525", true, findCategoriaById(categorias, "CAT-MON-HG432Z"), fichaTecnica.put("tamaño", "27 pulgadas").put("resolucion", "2560 x 1440").put("frecuenciaRefresco", "170Hz").put("tecnologiaPanel", "IPS")));
        productos.add(crearProducto("PROD-AER-PREDQW", "Acer Predator XB273U", "Monitor gaming de 27 pulgadas con resolución WQHD y frecuencia de actualización de 165Hz de la serie Predator de Acer, ofrece un rendimiento excepcional para juegos de alta velocidad.", BigDecimal.valueOf(599.99), "Acer", 20, "https://promart.vteximg.com.br/arquivos/ids/6736319-1000-1000/imageUrl_2.jpg?v=638114633780030000", true, findCategoriaById(categorias, "CAT-MON-HG432Z"), fichaTecnica.put("tamaño", "27 pulgadas").put("resolucion", "2560 x 1440").put("frecuenciaRefresco", "165Hz").put("tecnologiaPanel", "IPS")));
        productos.add(crearProducto("PROD-BNQ-EX270Q", "BenQ EX2780Q", "Monitor gaming de 27 pulgadas con resolución 2K y frecuencia de actualización de 144Hz de la serie EX de BenQ, ofrece una experiencia de juego fluida y colores vibrantes.", BigDecimal.valueOf(399.99), "BenQ", 25, "https://image.benq.com/is/image/benqco/ex2780q-front-2?$ResponsivePreset$&fmt=png-alpha", true, findCategoriaById(categorias, "CAT-MON-HG432Z"), fichaTecnica.put("tamaño", "27 pulgadas").put("resolucion", "2560 x 1440").put("frecuenciaRefresco", "144Hz").put("tecnologiaPanel", "IPS")));
        productos.add(crearProducto("PROD-LAG-27GL8A", "LG 27GL850-B", "Monitor gaming de 27 pulgadas con resolución QHD y frecuencia de actualización de 144Hz de la serie UltraGear de LG, ofrece un tiempo de respuesta rápido y una calidad de imagen excepcional.", BigDecimal.valueOf(449.99), "LG", 30, "https://www.lg.com/es/images/monitores/md06177556/gallery/27gl850-01.jpg", true, findCategoriaById(categorias, "CAT-MON-HG432Z"), fichaTecnica.put("tamaño", "27 pulgadas").put("resolucion", "2560 x 1440").put("frecuenciaRefresco", "144Hz").put("tecnologiaPanel", "Nano IPS")));
        productos.add(crearProducto("PROD-SAP-LC32GA", "Samsung Odyssey G7 LC32G75TQSNXZA", "Monitor gaming curvo de 32 pulgadas con resolución QHD y frecuencia de actualización de 240Hz de la serie Odyssey G7 de Samsung, ofrece una experiencia de juego inmersiva y de alta velocidad.", BigDecimal.valueOf(699.99), "Samsung", 35, "https://image-us.samsung.com/SamsungUS/samsungbusiness/products/computing/monitors/g-series/lc27g75tqsnxza/gallery/C27G75T_001_Front_Black_1200x1200.jpg?$support-product-hero-jpg$", true, findCategoriaById(categorias, "CAT-MON-HG432Z"), fichaTecnica.put("tamaño", "32 pulgadas").put("resolucion", "2560 x 1440").put("frecuenciaRefresco", "240Hz").put("tecnologiaPanel", "VA")));

        /* Coolers */
        productos.add(crearProducto("PROD-COR-HA100I", "CORSAIR Hydro Series H100i RGB Platinum", "Sistema de enfriamiento líquido todo en uno con radiador de 240 mm y dos ventiladores PWM de 120 mm, ofrece un rendimiento de refrigeración excepcional para procesadores de alto rendimiento en sistemas de gaming.", BigDecimal.valueOf(159.99), "CORSAIR", 20, "https://assets.corsair.com/image/upload/c_pad,q_auto,h_1024,w_1024,f_auto/products/Certified-Refurbished/CW-9060042-WW/Gallery/H100i_RGB_PLAT_SE_01.webp?width=1080&quality=85&auto=webp&format=pjpg", true, findCategoriaById(categorias, "CAT-COO-BC123W"), fichaTecnica.put("compatibilidad", "Intel, AMD").put("iluminacionRGB", "Sí").put("tipoEnfriamiento", "Líquido")));
        productos.add(crearProducto("PROD-NZT-KRKZ63", "NZXT Kraken Z63", "Sistema de enfriamiento líquido todo en uno con pantalla LCD de 2.36 pulgadas, radiador de 280 mm y dos ventiladores Aer P 140 mm, ofrece un control avanzado de la refrigeración y una estética personalizable para los entusiastas del gaming.", BigDecimal.valueOf(199.99), "NZXT", 25, "https://nzxt.com/assets/cms/34299/1615588736-kraken-z63frontbnwith-fanui.png?auto=format&fit=crop&h=1000&w=1000", true, findCategoriaById(categorias, "CAT-COO-BC123W"), fichaTecnica.put("compatibilidad", "Intel, AMD").put("iluminacionRGB", "Sí").put("tipoEnfriamiento", "Líquido")));
        productos.add(crearProducto("PROD-COL-ML360R", "Cooler Master MasterLiquid ML360R RGB", "Sistema de enfriamiento líquido todo en uno con radiador de 360 mm y tres ventiladores MF120R ARGB, ofrece una refrigeración potente y una iluminación vibrante para sistemas de gaming de alto rendimiento.", BigDecimal.valueOf(179.99), "Cooler Master", 30, "https://www.impacto.com.pe/storage/products/1641940542.jpg", true, findCategoriaById(categorias, "CAT-COO-BC123W"), fichaTecnica.put("compatibilidad", "Intel, AMD").put("iluminacionRGB", "Sí").put("tipoEnfriamiento", "Líquido")));
        productos.add(crearProducto("PROD-THE-RGDUAL", "Thermaltake Ring Duo 12 RGB", "Kit de ventiladores de refrigeración con dos ventiladores de 120 mm y un controlador de iluminación ARGB, ofrecen un flujo de aire potente y una iluminación personalizable para mejorar el rendimiento y la estética de tu PC gaming.", BigDecimal.valueOf(79.99), "Thermaltake", 35, "https://pisces.bbystatic.com/image2/BestBuy_US/images/products/6339/6339074_sd.jpg", true, findCategoriaById(categorias, "CAT-COO-BC123W"), fichaTecnica.put("compatibilidad", "Universal").put("iluminacionRGB", "Sí").put("tipoEnfriamiento", "Aire")));
        productos.add(crearProducto("PROD-DEE-GX40V2", "DEEPCOOL GAMMAXX 400 V2", "Disipador de calor de CPU con cuatro tubos de calor y ventilador PWM de 120 mm con iluminación LED azul, ofrece una refrigeración eficiente y silenciosa para procesadores gaming de gama media a alta.", BigDecimal.valueOf(49.99), "DEEPCOOL", 40, "https://www.infotec.com.pe/51656-large_default/cooler-para-procesador-deepcool-gammaxx-400-v2-red-dp-mch4-gmx400v2-rd-led-rojo.jpg", true, findCategoriaById(categorias, "CAT-COO-BC123W"), fichaTecnica.put("compatibilidad", "Intel, AMD").put("iluminacionRGB", "No").put("tipoEnfriamiento", "Aire")));


        /*Fuentes de alimentación*/
        productos.add(crearProducto("PROD-COR-RM850X", "CORSAIR RM850x", "Fuente de alimentación modular de 850W con certificación 80 Plus Gold, diseñada para ofrecer un suministro de energía estable y eficiente para sistemas de gaming de alto rendimiento.", BigDecimal.valueOf(139.99), "CORSAIR", 20, "https://store.teslards.pe/wp-content/uploads/2022/11/71Bam0yVOSS._AC_SL1500_.jpg", true, findCategoriaById(categorias, "CAT-PSU-VN876P"), fichaTecnica.put("potencia", "850W").put("certificacion", "80 Plus Gold").put("modularidad", "Sí")));
        productos.add(crearProducto("PROD-EGA-SUPERN", "EVGA SuperNOVA 750 G5", "Fuente de alimentación totalmente modular de 750W con certificación 80 Plus Gold, ofrece una eficiencia excepcional y un rendimiento estable para sistemas de gaming exigentes.", BigDecimal.valueOf(129.99), "EVGA", 25, "https://images.evga.com/products/gallery/png/220-G5-0750-X1_LG_1.png", true, findCategoriaById(categorias, "CAT-PSU-VN876P"), fichaTecnica.put("potencia", "750W").put("certificacion", "80 Plus Gold").put("modularidad", "Sí")));
        productos.add(crearProducto("PROD-SEC-FOCU11", "Seasonic Focus Plus 850 Gold", "Fuente de alimentación totalmente modular de 850W con certificación 80 Plus Gold, garantiza una distribución de energía estable y eficiente para sistemas de gaming de alta gama.", BigDecimal.valueOf(149.99), "Seasonic", 30, "https://http2.mlstatic.com/D_NQ_NP_831712-MPE70393736610_072023-O.webp", true, findCategoriaById(categorias, "CAT-PSU-VN876P"), fichaTecnica.put("potencia", "850W").put("certificacion", "80 Plus Gold").put("modularidad", "Sí")));
        productos.add(crearProducto("PROD-COO-MWE850", "Cooler Master MWE 850 Gold", "Fuente de alimentación modular de 850W con certificación 80 Plus Gold, ofrece una entrega de energía confiable y eficiente para sistemas de gaming de alto rendimiento.", BigDecimal.valueOf(119.99), "Cooler Master", 35, "https://dojiw2m9tvv09.cloudfront.net/71228/product/11378.png", true, findCategoriaById(categorias, "CAT-PSU-VN876P"), fichaTecnica.put("potencia", "850W").put("certificacion", "80 Plus Gold").put("modularidad", "Sí")));
        productos.add(crearProducto("PROD-ASS-AS1210", "ASUS ROG Thor 850P", "Fuente de alimentación modular de 850W con certificación 80 Plus Platinum y diseño con iluminación RGB integrada, proporciona un suministro de energía estable y eficiente con un aspecto visual impresionante para sistemas de gaming de gama alta.", BigDecimal.valueOf(219.99), "ASUS", 40, "https://store.teslards.pe/wp-content/uploads/2021/05/D_NQ_NP_798013-MPE31893258115_082019-O.jpg", true, findCategoriaById(categorias, "CAT-PSU-VN876P"), fichaTecnica.put("potencia", "850W").put("certificacion", "80 Plus Platinum").put("modularidad", "Sí").put("iluminacionRGB", "Sí")));


        /*CASES*/
        productos.add(crearProducto("PROD-NXT-H51210", "NZXT H510", "Case compacto para PC con panel frontal de vidrio templado y sistema de gestión de cables integrado, ofrece un diseño elegante y funcional para construcciones de PC gaming.", BigDecimal.valueOf(79.99), "NZXT", 20, "https://nzxt.com/assets/cms/34299/1617970872-h510-white-black-mainw-system.png?auto=format&fit=crop&h=1000&w=1000", true, findCategoriaById(categorias, "CAT-CAE-LM435R"), fichaTecnica.put("tamaño", "Mid Tower").put("ventiladoresIncluidos", "2").put("compatibilidad", "ATX, MicroATX, Mini-ITX")));
        productos.add(crearProducto("PROD-COS-27AA5R", "CORSAIR Carbide Series 275R", "Case de media torre con panel lateral de vidrio templado y espacio para hasta seis ventiladores, ofrece una construcción robusta y opciones de enfriamiento versátiles para sistemas gaming de alto rendimiento.", BigDecimal.valueOf(89.99), "CORSAIR", 25, "https://assets.corsair.com/image/upload/f_auto,q_auto/content/CC-9011132-WW-275R-G-04-built.png", true, findCategoriaById(categorias, "CAT-CAE-LM435R"), fichaTecnica.put("tamaño", "Mid Tower").put("ventiladoresIncluidos", "1").put("compatibilidad", "ATX, MicroATX, Mini-ITX")));
        productos.add(crearProducto("PROD-COO-TD5A00", "Cooler Master MasterBox TD500", "Case de torre completa con panel frontal de malla y tres ventiladores ARGB preinstalados, ofrece un flujo de aire superior y un diseño estético para construcciones de PC gaming llamativas.", BigDecimal.valueOf(99.99), "Cooler Master", 30, "https://www.yamoshi.com.pe/22617-large_default/case-cooler-master-masterbox-td500-mesh-white-mcb-d500d-wgnn-s01-argb.jpg", true, findCategoriaById(categorias, "CAT-CAE-LM435R"), fichaTecnica.put("tamaño", "Full Tower").put("ventiladoresIncluidos", "3").put("compatibilidad", "ATX, MicroATX, Mini-ITX")));
        productos.add(crearProducto("PROD-AKE-V21100", "Thermaltake V200 Tempered Glass RGB", "Case con panel lateral de vidrio templado y tres ventiladores RGB preinstalados, ofrece una iluminación vibrante y opciones de enfriamiento eficientes para construcciones de PC gaming de gama media a alta.", BigDecimal.valueOf(109.99), "Thermaltake", 35, "https://www.example.com/path/to/image4.jpg", true, findCategoriaById(categorias, "CAT-CAE-LM435R"), fichaTecnica.put("tamaño", "Mid Tower").put("ventiladoresIncluidos", "3").put("compatibilidad", "ATX, MicroATX, Mini-ITX")));
        productos.add(crearProducto("PROD-UGR-GEMINI", "Cougar Gemini T PRO", "Case compacto con diseño de doble cámara para una gestión eficiente del flujo de aire y enfriamiento, ofrece un aspecto elegante y funcional para construcciones de PC gaming compactas.", BigDecimal.valueOf(69.99), "Cougar", 40, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS7JujKmrcseo6Qd2Mkcfzf6xeziHoI4CxH7JB2oIaGNn3LTK6lIrpkH7KuDbevtA_BrHg", true, findCategoriaById(categorias, "CAT-CAE-LM435R"), fichaTecnica.put("tamaño", "MicroATX").put("ventiladoresIncluidos", "1").put("compatibilidad", "MicroATX, Mini-ITX")));


        /*SILLAS*/
        productos.add(crearProducto("PROD-SLL-912KAS", "AKRacing Masters Serie Silla Gaming Pro\n", "Silla ergonómica diseñada para proporcionar comodidad durante largas sesiones de juego. Características incluyen soporte lumbar ajustable, reposabrazos acolchados y reclinación ajustable para una experiencia de juego cómoda.", BigDecimal.valueOf(199.99), "AKRACING", 50, "https://es.akracing.com/cdn/shop/products/PRO-blackblue-2_863d3e02-553d-4d40-a44d-3010694224ba_1800x1800.png?v=1583448269", true, findCategoriaById(categorias, "CAT-SLL-PL678E"), fichaTecnica.put("material", "Cuero sintético").put("capacidadPeso", "120 kg").put("dimensiones", "70 x 60 x 120 cm")));
        productos.add(crearProducto("PROD-SLL-1KSA1O", "Silla Gamer EliteTech Pro", "Silla con diseño deportivo y estructura robusta. Incluye soporte lumbar integrado, reposabrazos ajustables y acolchados, así como reclinación de hasta 180 grados para mayor confort.", BigDecimal.valueOf(249.99), "NXT", 40, "https://falabella.scene7.com/is/image/FalabellaPE/gsc_128279450_5136306_1?wid=400&hei=400&qlt=70", true, findCategoriaById(categorias, "CAT-SLL-PL678E"), fichaTecnica.put("material", "Cuero sintético").put("capacidadPeso", "150 kg").put("dimensiones", "75 x 65 x 130 cm")));
        productos.add(crearProducto("PROD-SLL-ZM1AS1", "Silla Gamer con Masajeador Lumbar Playpro X1 Azul Base de Metal", "Silla diseñada para ofrecer la máxima comodidad durante las sesiones de juego prolongadas. Cuenta con respaldo ajustable, cojines lumbares y cervicales, y reposabrazos 4D para adaptarse a las preferencias del usuario.", BigDecimal.valueOf(299.99), "PLAYPRO", 30, "https://falabella.scene7.com/is/image/FalabellaPE/gsc_122889295_3466009_2?wid=800&hei=800&qlt=70", true, findCategoriaById(categorias, "CAT-SLL-PL678E"), fichaTecnica.put("material", "Cuero sintético").put("capacidadPeso", "130 kg").put("dimensiones", "80 x 70 x 125 cm")));
        productos.add(crearProducto("PROD-SLL-004ASF", "Silla Gamer Dreizt Titan Series Negro Premium Almohadilla Inteligente", "Silla ergonómica con diseño moderno y funcionalidades avanzadas. Incluye sistema de masaje integrado, reposapiés retráctil, y soporte lumbar y cervical ajustable para proporcionar una experiencia de juego óptima.", BigDecimal.valueOf(349.99), "DREIZT", 20, "https://falabella.scene7.com/is/image/FalabellaPE/gsc_117543285_1799507_2?wid=800&hei=800&qlt=70", true, findCategoriaById(categorias, "CAT-SLL-PL678E"), fichaTecnica.put("material", "Cuero sintético").put("capacidadPeso", "140 kg").put("dimensiones", "85 x 75 x 135 cm")));
        productos.add(crearProducto("PROD-SLL-00XAS5", "SILLA GAMER ANTRYX RECLINABLE CON ALMOHADILLA XTREME RACING NOVA BLACK", "Silla gaming con diseño aerodinámico y confortable. Dispone de soporte lumbar ajustable, reposabrazos 3D y cojín de masaje para garantizar una experiencia de juego placentera durante horas.", BigDecimal.valueOf(279.99), "ANTRYX", 25, "https://falabella.scene7.com/is/image/FalabellaPE/gsc_128485679_5218434_1?wid=800&hei=800&qlt=70", true, findCategoriaById(categorias, "CAT-SLL-PL678E"), fichaTecnica.put("material", "Cuero sintético").put("capacidadPeso", "135 kg").put("dimensiones", "78 x 68 x 128 cm")));


        return productos;
    }

    public List<RolEntity> initRoles() {
        List<RolEntity> roles = new ArrayList<>();
        roles.add(crearRol("ROL-ADM-PUT001", "Administrador", "Rol con permisos de administración y control total del sistema.", true));
        roles.add(crearRol("ROL-EMP-ALKM02", "Empleado", "Rol con permisos limitados para la gestión de productos y ventas.", true));
        roles.add(crearRol("ROL-CLI-VLA003", "Cliente", "Rol con permisos limitados para la visualización y compra de productos.", true));
        return roles;
    }

    public List<TipoDocumentoIdentidadEntity> initTipoDocumentos() {
        List<TipoDocumentoIdentidadEntity> tipoDocumentos = new ArrayList<>();
        tipoDocumentos.add(crearTipoDocumento("TID-DNI-AMO001", "DNI", true));
        return tipoDocumentos;
    }

    public List<EmpleadoEntity> initEmpleados(List<RolEntity> roles, List<TipoDocumentoIdentidadEntity> tipoDocumentos) {
        List<EmpleadoEntity> empleados = new ArrayList<>();
        String passwordAdmin = passwordEncoder.encode("Superadmin123");

        // Rol Admin
        empleados.add(crearEmpleado("EMP-ADM-1K001", "Admin", "Admin", "Calle 123, Ciudad", "923456789", "importacionesDominguez2024@gmail.com", (passwordEncoder.encode("Superadmin123")), findTipoDocumentoById(tipoDocumentos, "TID-DNI-AMO001"), TipoSexo.valueOf("MASCULINO"), "87654372", findRolById(roles, "ROL-ADM-PUT001"), true));
        empleados.add(crearEmpleado("EMP-JUP-1AS001", "Juan", "Pérez Montes", "Calle 123, Ciudad", "913456789", "juanPerez@gmail.com", passwordEncoder.encode("Password123"), findTipoDocumentoById(tipoDocumentos, "TID-DNI-AMO001"), TipoSexo.valueOf("MASCULINO"), "87652372", findRolById(roles, "ROL-EMP-ALKM02"), true));
        empleados.add(crearEmpleado("EMP-ANA-2LK002", "Ana", "López Flores", "Av. 456, Ciudad", "987654321", "anaPerez@gmail.com", passwordEncoder.encode("Password123"), findTipoDocumentoById(tipoDocumentos, "TID-DNI-AMO001"), TipoSexo.valueOf("FEMENINO"), "87654371", findRolById(roles, "ROL-EMP-ALKM02"), true));
        empleados.add(crearEmpleado("EMP-ROB-3DF003", "Roberto", "Díaz  Manizales", "Calle 789, Ciudad", "956789123", "robertDiaz@gmail.com", passwordEncoder.encode("Password123"), findTipoDocumentoById(tipoDocumentos, "TID-DNI-AMO001"), TipoSexo.valueOf("MASCULINO"), "87354352", findRolById(roles, "ROL-EMP-ALKM02"), true));
        empleados.add(crearEmpleado("EMP-CLA-4GH004", "Claudia", "Gómez Rodriguez", "Av. 789, Ciudad", "989123456", "clauGomez@hotmail.com", passwordEncoder.encode("Password123"), findTipoDocumentoById(tipoDocumentos, "TID-DNI-AMO001"), TipoSexo.valueOf("FEMENINO"), "85654372", findRolById(roles, "ROL-EMP-ALKM02"), true));
        return empleados;
    }

    public List<ClienteEntity> initClients(List<RolEntity> roles, List<TipoDocumentoIdentidadEntity> tipoDocumentos) {
        List<ClienteEntity> clientes = new ArrayList<>();
        clientes.add(crearCliente("CLI-JUP-1AS001", "Juana", "Pérez Sosa", "Calle 123, Ciudad", "903456789", "juanPer@example.com", passwordEncoder.encode("Password123"), findTipoDocumentoById(tipoDocumentos, "TID-DNI-AMO001"), TipoSexo.valueOf("MASCULINO"), "87652372", findRolById(roles, "ROL-CLI-VLA003"), true));
        clientes.add(crearCliente("CLI-ANA-2LK002", "Ana", "Manolete Mendoza", "Av. 456, Ciudad", "987654321", "anaManol@gmail.com", passwordEncoder.encode("Password123"), findTipoDocumentoById(tipoDocumentos, "TID-DNI-AMO001"), TipoSexo.valueOf("FEMENINO"), "87654371", findRolById(roles, "ROL-CLI-VLA003"), true));
        clientes.add(crearCliente("CLI-ROB-3DF003", "Manuel", "Díaz Lopez", "Calle 789, Ciudad", "956389123", "manuLopez@yahoo.com", passwordEncoder.encode("Password123"), findTipoDocumentoById(tipoDocumentos, "TID-DNI-AMO001"), TipoSexo.valueOf("MASCULINO"), "87354352", findRolById(roles, "ROL-CLI-VLA003"), true));
        clientes.add(crearCliente("CLI-CLA-4GH004", "Claudia", "Saenz Peña", "Av. 789, Ciudad", "989023456", "claudiaSaez@example.com", passwordEncoder.encode("Password123"), findTipoDocumentoById(tipoDocumentos, "TID-DNI-AMO001"), TipoSexo.valueOf("FEMENINO"), "85654372", findRolById(roles, "ROL-CLI-VLA003"), true));
        return clientes;
    }

    public List<TipoTransaccionEntity> initTransacciones() {
        List<TipoTransaccionEntity> tipoTransacciones = new ArrayList<>();
        tipoTransacciones.add(crearTipoTransaccion("TTR-VENTA-TOC201", "Boleta", true));
        return tipoTransacciones;
    }

    public List<ProveedorEntity> initProveedores(List<TipoDocumentoIdentidadEntity> tipoDocumentos) {
        List<ProveedorEntity> proveedores = new ArrayList<>();
        // Proveedores como ejemplo Mayoristas o personas que venden productos
        proveedores.add(crearProveedor("PROV-AMD-1K001", "AMDCenter", "Calle Arequipa 123, Ciudad", "123456789", "AMDCenter@gmail.com", findTipoDocumentoById(tipoDocumentos, "TID-DNI-AMO001"), "89675432", true));
        proveedores.add(crearProveedor("PROV-INT-1AS001", "IntelCenter", "Calle Arequipa 123, Ciudad", "123456789", "IntelMarCenter@gmail.com", findTipoDocumentoById(tipoDocumentos, "TID-DNI-AMO001"), "89675432", true));
        return proveedores;
    }


    /* METODOS AUXILIARES */
    public static CategoriaEntity findCategoriaById(List<CategoriaEntity> categorias, String id) {
        for (CategoriaEntity categoria : categorias) {
            if (categoria.getId().equals(id)) {
                return categoria;
            }
        }
        return null;
    }

    public static RolEntity findRolById(List<RolEntity> roles, String id) {
        for (RolEntity rol : roles) {
            if (rol.getId().equals(id)) {
                return rol;
            }
        }
        return null;
    }

    public static TipoDocumentoIdentidadEntity findTipoDocumentoById(List<TipoDocumentoIdentidadEntity> tipoDocumentos, String id) {
        for (TipoDocumentoIdentidadEntity tipoDocumento : tipoDocumentos) {
            if (tipoDocumento.getId().equals(id)) {
                return tipoDocumento;
            }
        }
        return null;
    }


    /* CREACION DE LAS ENTIDADES */

    public static ProductoEntity crearProducto(String id, String modelo, String descripcion, BigDecimal precio, String marca, int stock, String imagen, boolean estado, CategoriaEntity categoria, JsonNode fichaTecnica) {
        return ProductoEntity.builder()
                .id(id)
                .modelo(modelo)
                .descripcion(descripcion)
                .precio(precio)
                .marca(marca)
                .stock(stock)
                .imagen(imagen)
                .estado(estado)
                .categoria(categoria)
                .fichaTecnica(fichaTecnica)
                .build();
    }

    public static TipoDocumentoIdentidadEntity crearTipoDocumento(String id, String nombre, boolean estado) {
        return TipoDocumentoIdentidadEntity.builder()
                .id(id)
                .nombre(nombre)
                .estado(estado)
                .build();
    }

    public static ProveedorEntity crearProveedor(String id, String nombres, String direccion, String telefono, String email, TipoDocumentoIdentidadEntity tipoDocumento, String numeroDocumento, boolean estado) {
        return ProveedorEntity.builder()
                .id(id)
                .nombres(nombres)
                .direccion(direccion)
                .telefono(telefono)
                .email(email)
                .tipoDocumento(tipoDocumento)
                .numeroDocumento(numeroDocumento)
                .estado(estado)
                .build();
    }

    public static RolEntity crearRol(String id, String nombre, String descripcion, boolean estado) {
        return RolEntity.builder()
                .id(id)
                .nombre(nombre)
                .descripcion(descripcion)
                .estado(estado)
                .build();
    }

    public static ClienteEntity crearCliente(String id, String nombres, String apellidos, String direccion, String telefono, String email, String password, TipoDocumentoIdentidadEntity tipoDocumento, TipoSexo tipoSexo, String numeroDocumento, RolEntity rol, boolean estado) {
        return ClienteEntity.builder()
                .id(id)
                .nombres(nombres)
                .apellidos(apellidos)
                .direccion(direccion)
                .telefono(telefono)
                .email(email)
                .password(password)
                .tipoDocumento(tipoDocumento)
                .numeroDocumento(numeroDocumento)
                .sexo(tipoSexo)
                .rol(rol)
                .estado(estado)
                .build();
    }

    public static EmpleadoEntity crearEmpleado(String id, String nombres, String apellidos, String direccion, String telefono, String email, String password, TipoDocumentoIdentidadEntity tipoDocumento, TipoSexo tipoSexo, String numeroDocumento, RolEntity rol, boolean estado) {
        return EmpleadoEntity.builder()
                .id(id)
                .nombres(nombres)
                .apellidos(apellidos)
                .direccion(direccion)
                .telefono(telefono)
                .email(email)
                .password(password)
                .tipoDocumento(tipoDocumento)
                .numeroDocumento(numeroDocumento)
                .sexo(tipoSexo)
                .rol(rol)
                .estado(estado)
                .build();
    }

    public static TipoTransaccionEntity crearTipoTransaccion(String id, String nombre, boolean estado) {
        return TipoTransaccionEntity.builder()
                .id(id)
                .nombre(nombre)
                .estado(estado)
                .build();
    }

    public static MetodoPagoEntity createMetodoPago(String id, String nombre, boolean estado) {
        return MetodoPagoEntity.builder()
                .id(id)
                .nombre(nombre)
                .estado(estado)
                .build();
    }
}
