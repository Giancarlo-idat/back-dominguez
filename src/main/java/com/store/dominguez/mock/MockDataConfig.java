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
        } else {
            System.out.println("La base de datos ya contiene los datos.");
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
        categorias.add(new CategoriaEntity("CAT-PROC-AS120K", "Procesadores", "Unidad central de procesamiento (CPU) que ejecuta las instrucciones de un programa y realiza cálculos.", true, "procesadores.jpg"));
        categorias.add(new CategoriaEntity("CAT-TJG-ZX321F", "Tarjetas gráficas", "Componente que procesa y genera imágenes para ser mostradas en la pantalla.", true, "tarjetas_graficas.jpg"));
        categorias.add(new CategoriaEntity("CAT-PLAC-YH874B", "Placas base", "Placa de circuito impreso que conecta todos los componentes de hardware de una computadora.", true, "placas_base.jpg"));
        categorias.add(new CategoriaEntity("CAT-RAM-QW567D", "Memoria RAM", "Memoria de acceso aleatorio utilizada para almacenar datos y programas en uso por la CPU.", true, "memoria_ram.jpg"));
        categorias.add(new CategoriaEntity("CAT-ALMA-PL678E", "Almacenamiento", "Dispositivos de almacenamiento utilizados para guardar datos de manera permanente en una computadora.", true, "almacenamiento.jpg"));
        categorias.add(new CategoriaEntity("CAT-PERI-UY989T", "Periféricos", "Dispositivos conectados a una computadora que le permiten interactuar con su entorno.", true, "perifericos.jpg"));
        categorias.add(new CategoriaEntity("CAT-MON-HG432Z", "Monitores", "Pantalla de visualización utilizada para mostrar imágenes generadas por la computadora.", true, "monitores.jpg"));
        categorias.add(new CategoriaEntity("CAT-SON-BC123W", "Tarjetas de sonido", "Dispositivo de hardware que permite la reproducción y grabación de audio en una computadora.", true, "tarjetas_sonido.jpg"));
        categorias.add(new CategoriaEntity("CAT-PSU-VN876P", "Fuentes de alimentación", "Componente que suministra energía eléctrica a los componentes de una computadora.", true, "fuentes_alimentacion.jpg"));
        categorias.add(new CategoriaEntity("CAT-CASE-LM435R", "Cajas", "Estructura que aloja y protege los componentes de una computadora.", true, "cajas.jpg"));
        return categorias;
    }

    public List<ProductoEntity> initProducts(List<CategoriaEntity> categorias) {
        List<ProductoEntity> productos = new ArrayList<>();

        JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode fichaTecnica = factory.objectNode();

        productos.add(crearProducto("PROD-ITC-1K1001", "Intel Core i9-11900K", "Procesador de última generación para computadoras de alto rendimiento.", BigDecimal.valueOf(499.99), "Intel", 20, "https://www.hbltecstore.pe/cdn/shop/files/71Qc91x5b8L._AC_SX679.jpg?v=1683148042", true, findCategoriaById(categorias, "CAT-PROC-AS120K"), fichaTecnica.put("frecuencia", "3.5 GHz").put("nucleos", "8").put("hilos", "16")));

        productos.add(crearProducto("PROD-RTX-0LÑM02", "NVIDIA GeForce RTX 3080", "Potente tarjeta gráfica para juegos de alta calidad y aplicaciones de renderizado.", BigDecimal.valueOf(799.99), "NVIDIA", 15, "https://smartbusiness.pe/cdn/shop/products/81UBILsWwiS._AC_SL1500.jpg?v=1702514253", true, findCategoriaById(categorias, "CAT-TJG-ZX321F"), fichaTecnica.put("memoria", "10GB GDDR6X").put("frecuencia", "1.71 GHz").put("conexiones", "HDMI, DisplayPort")));

        productos.add(crearProducto("PROD-ROG-002D03", "ASUS ROG Strix Z590-E Gaming", "Placa base de alta gama con todas las características para gamers y entusiastas.", BigDecimal.valueOf(349.99), "ASUS", 10, "https://cyccomputer.pe/33292-large_default/placa-asus-rog-strix-z590-e-gaming-wifi-ddr4-lga-1200-pn90mb1640-m0eayo.jpg", true, findCategoriaById(categorias, "CAT-PLAC-YH874B"), fichaTecnica.put("factor_forma", "ATX").put("dimensiones", "30.5 x 24.4 cm").put("peso", "1.5 kg")));

        productos.add(crearProducto("PROD-COR-0MAS04", "Corsair Vengeance RGB Pro 32GB (2x16GB)", "Memoria RAM de alto rendimiento con iluminación RGB.", BigDecimal.valueOf(199.99), "Corsair", 30, "https://m.media-amazon.com/images/I/61GpY38PAWL._AC_SL1200_.jpg", true, findCategoriaById(categorias, "CAT-RAM-QW567D"), fichaTecnica.put("capacidad", "32GB").put("tipo", "DDR4").put("velocidad", "3600 MHz")));

        productos.add(crearProducto("PROD-SAM-0SUNG5", "Samsung 970 EVO Plus 1TB NVMe SSD", "Unidad de estado sólido NVMe de alta velocidad y capacidad de almacenamiento.", BigDecimal.valueOf(149.99), "Samsung", 25, "https://http2.mlstatic.com/D_NQ_NP_884540-MLA51400647546_092022-O.webp", true, findCategoriaById(categorias, "CAT-ALMA-PL678E"), fichaTecnica.put("capacidad", "1TB").put("tipo", "NVMe").put("lectura", "3500 MB/s").put("escritura", "3300 MB/s")));

        productos.add(crearProducto("PROD-LOG-0T2E06", "Logitech G502 HERO Ratón Gaming", "Ratón gaming con sensor HERO 25K, 11 botones programables y sistema de peso ajustable.", BigDecimal.valueOf(79.99), "Logitech", 50, "https://www.impacto.com.pe/storage/products/1611332760910-005469-6.jpg", true, findCategoriaById(categorias, "CAT-PERI-UY989T"), fichaTecnica.put("sensor", "HERO 25K").put("botones", "11 programables").put("peso_ajustable", "Sí")));

        productos.add(crearProducto("PROD-UGR-MWS007", "LG UltraGear 27GN950-B", "Monitor gaming 4K Ultra HD con HDR, 144Hz de frecuencia de actualización y 1ms de tiempo de respuesta.", BigDecimal.valueOf(799.99), "LG", 20, "https://www.lg.com/content/dam/channel/wcms/pe/images/monitores/27gr75q-b_awf_espr_pe_c/gallery/D-01.jpg", true, findCategoriaById(categorias, "CAT-MON-HG432Z"), fichaTecnica.put("tamaño", "27 pulgadas").put("resolucion", "3840x2160").put("frecuencia", "144Hz").put("hdr", "Sí")));

        productos.add(crearProducto("PROD-CREA-0PLM08", "Creative Sound BlasterX AE-5 Plus", "Tarjeta de sonido PCIe con amplificador de auriculares, iluminación RGB y software de personalización.", BigDecimal.valueOf(149.99), "Creative", 15, "https://d287ku8w5owj51.cloudfront.net/images/products/hero/others/hero-sound-blasterx-ae-5-plus.jpg?width=400&height=400", true, findCategoriaById(categorias, "CAT-SON-BC123W"), fichaTecnica.put("amplificador_auriculares", "Sí").put("iluminacion_rgb", "Sí").put("software", "Sound Blaster Command")));

        productos.add(crearProducto("PROD-009-3NLA18", "AMD Ryzen 7 5800X", "Procesador de alto rendimiento para usuarios exigentes y entusiastas del gaming.", BigDecimal.valueOf(449.99), "AMD", 25, "https://www.sercoplus.com/18716-medium_default/procesador-amd-ryzen-7-5800x.jpg", true, findCategoriaById(categorias, "CAT-PROC-AS120K"), fichaTecnica.put("frecuencia", "3.8 GHz").put("nucleos", "8").put("hilos", "16")));

        productos.add(crearProducto("PROD-010-PPMM28", "Gigabyte GeForce GTX 1660 Super OC 6G", "Tarjeta gráfica de nivel medio con excelente rendimiento en juegos a 1080p.", BigDecimal.valueOf(299.99), "Gigabyte", 20, "https://promart.vteximg.com.br/arquivos/ids/7419404-1000-1000/image-b3678c7f70a04160b1b594e046e17ca4.jpg?v=638272803429970000", true, findCategoriaById(categorias, "CAT-TJG-ZX321F"), fichaTecnica.put("memoria", "6GB GDDR6").put("frecuencia", "1830 MHz").put("conexiones", "HDMI, DisplayPort")));

        productos.add(crearProducto("PROD-011-PACP18", "MSI B450 TOMAHAWK MAX", "Placa base de gama media con sólido rendimiento y gran capacidad de expansión.", BigDecimal.valueOf(129.99), "MSI", 15, "https://asset.msi.com/resize/image/global/product/product_1_20200806161944_5f2bbd20c4441.png62405b38c58fe0f07fcef2367d8a9ba1/600.png", true, findCategoriaById(categorias, "CAT-PLAC-YH874B"), fichaTecnica.put("factor_forma", "ATX").put("dimensiones", "30.5 x 24.4 cm").put("peso", "1.2 kg")));

        productos.add(crearProducto("PROD-012-0PAL18", "Crucial Ballistix RGB 16GB (2x8GB) DDR4 3200 MHz", "Memoria RAM con iluminación RGB y excelente rendimiento para juegos y aplicaciones.", BigDecimal.valueOf(99.99), "Crucial", 30, "https://m.media-amazon.com/images/I/71iTXK+7q+L._AC_UF894,1000_QL80_.jpg", true, findCategoriaById(categorias, "CAT-RAM-QW567D"), fichaTecnica.put("capacidad", "16GB").put("tipo", "DDR4").put("velocidad", "3200 MHz")));

        productos.add(crearProducto("PROD-013-0PLLA1", "Western Digital WD Blue 1TB SATA SSD", "Unidad de estado sólido SATA de alta velocidad y capacidad para almacenamiento general.", BigDecimal.valueOf(109.99), "Western Digital", 25, "https://sahuaperu.com.pe/wp-content/uploads/2023/11/WESTERN-DIGITAL-BLUE-WDS100T3B0A-1TB-e.jpg", true, findCategoriaById(categorias, "CAT-ALMA-PL678E"), fichaTecnica.put("capacidad", "1TB").put("tipo", "SATA").put("lectura", "560 MB/s").put("escritura", "530 MB/s")));

        productos.add(crearProducto("PROD-014-AM1208", "Razer DeathAdder V2 Ratón Gaming", "Ratón gaming con sensor óptico de alta precisión, switches ópticos y diseño ergonómico.", BigDecimal.valueOf(69.99), "Razer", 40, "https://www.impacto.com.pe/storage/products/1611159846RZ01-03210100-R3U1-4.jpg", true, findCategoriaById(categorias, "CAT-PERI-UY989T"), fichaTecnica.put("sensor", "Óptico").put("botones", "8 programables").put("iluminacion_rgb", "Sí")));

        productos.add(crearProducto("PROD-015-EMAN92", "Dell S2721DGF", "Monitor gaming QHD con 165Hz de frecuencia de actualización, compatibilidad G-Sync y FreeSync.", BigDecimal.valueOf(429.99), "Dell", 20, "https://ecommerce.datablitz.com.ph/cdn/shop/products/update-image-screenfill-monitor-s2721dgf-2000x1500-v4.jpg?v=1676869130", true, findCategoriaById(categorias, "CAT-MON-HG432Z"), fichaTecnica.put("tamaño", "27 pulgadas").put("resolucion", "2560x1440").put("frecuencia", "165Hz").put("gsync_freesync", "Sí")));

        productos.add(crearProducto("PROD-016-N1P238", "Creative Sound BlasterX G6", "Tarjeta de sonido USB externa con decodificación de audio de alta resolución y virtualización de sonido envolvente.", BigDecimal.valueOf(149.99), "Creative", 15, "https://http2.mlstatic.com/D_NQ_NP_959947-MPE52666135221_112022-O.webp", true, findCategoriaById(categorias, "CAT-SON-BC123W"), fichaTecnica.put("resolucion_audio", "32-bit/384kHz").put("virtualizacion", "Sí").put("compatibilidad", "PC, PS4, Nintendo Switch")));

        productos.add(crearProducto("PROD-017-ZOLSN1", "Corsair RM750x 750W 80 Plus Gold", "Fuente de alimentación modular con certificación 80 Plus Gold y ventilador de bajo ruido.", BigDecimal.valueOf(119.99), "Corsair", 30, "corsair_rm750x.jpg", true, findCategoriaById(categorias, "CAT-PSU-VN876P"), fichaTecnica.put("potencia", "750W").put("certificacion", "80 Plus Gold").put("modular", "Sí")));

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
