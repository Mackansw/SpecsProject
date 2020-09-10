package mackansw.tool;

import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;

import java.util.List;

public class Specs {

    private OperatingSystem os;
    private CentralProcessor cpu;
    private List<PhysicalMemory> ram;
    private List<GraphicsCard> graphicsCards;
    private List<HWDiskStore> storage;

    /**
     * Constructor with systemInfo parameter
     * @param systemInfo the systemInfo object
     */
    public Specs(SystemInfo systemInfo) {
        this.os = systemInfo.getOperatingSystem();
        this.cpu = systemInfo.getHardware().getProcessor();
        this.ram = systemInfo.getHardware().getMemory().getPhysicalMemory();
        this.storage = systemInfo.getHardware().getDiskStores();
        this.graphicsCards = systemInfo.getHardware().getGraphicsCards();
    }

    /**
     * Converts hertz to Gigahertz
     * @param value hertz to convert
     * @return the converted gigahertz
     */
    private double hertzToGigaHertz(double value) {
        double temp;
        temp = value / 1000000000;
        temp = Math.round(temp * 10) / 10.0;
        return temp;
    }

    /**
     * Converts byte to gigabyte
     * @param convert byte to convert
     * @param divideBy binary system to divide by(1000 or 1024)
     * @return the converted gigabyte
     */
    private double byteToGigabyte(double convert, int divideBy) {
        return convert / divideBy / divideBy / divideBy;
    }

    /**
     * Gets information about the machines OS
     * @return the OS name, version, and manufacturer
     */
    public String getOSInformation() {
        return "OS Name: " + System.getProperty("os.name") + "\n" + "OS Version: " + this.os.getVersionInfo() + "\n" + "OS Manufacturer: " + this.os.getManufacturer();
    }

    /**
     * Gets information about the machines CPU
     * @return the CPU name and amount of cores
     */
    public String getCPUInformation() {
        return "CPU: " + this.cpu.getProcessorIdentifier().getName() + "\n" + "CPU cores: " + this.cpu.getPhysicalProcessorCount();
    }

    /**
     * Gets information about the machines GPU
     * @return the GPU modelName and RAM
     */
    public String getGPUInformation() {
        StringBuilder result = new StringBuilder();
        double memory = 0;
        for(GraphicsCard graphicsCard : graphicsCards) {
            memory = Math.round(byteToGigabyte(graphicsCard.getVRam(), 1024));
            result.append("GPU: ").append(graphicsCard.getName()).append(", ").append("Memory: ").append(memory).append(" GB\n");
        }
        return result.toString();
    }

    /**
     * Gets information about the machines Storage devices
     * @return disks model and space
     */
    public String getStorageInformation() {
        StringBuilder result = new StringBuilder();
        double diskSpace = 0;
        for(HWDiskStore disk : storage) {
            diskSpace = Math.round(byteToGigabyte(disk.getSize(), 1000));
            result.append("Storage: ").append(disk.getModel()).append(" ").append(diskSpace).append(" GB\n");
        }
        return result.toString();
    }

    /**
     * Gets information about the machines RAMs
     * @return RAMS manufacturer, memoryType, memoryCapacity, clock speed, memory bank and machines total installed RAM
     */
    public String getRAMInformation() {
        StringBuilder result = new StringBuilder();
        double totalRam = 0;
        for(PhysicalMemory pm : ram) {
            result.append("RAM: ").append(pm.getManufacturer()).append(", ").append(pm.getMemoryType()).append(", ").append(byteToGigabyte(pm.getCapacity(), 1024)).append(" GB").append(", ").append(hertzToGigaHertz(pm.getClockSpeed())).append(" GHz").append(", ").append(pm.getBankLabel()).append("\n");
            totalRam += pm.getCapacity();
        }
        totalRam = Math.round(byteToGigabyte(totalRam, 1024));
        return "Total RAM installed " + totalRam + " GB\n" + result.toString();
    }
}