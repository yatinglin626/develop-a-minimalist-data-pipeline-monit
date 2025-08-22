/**
 * i49t_develop_a_minim.kt
 * A minimalist data pipeline monitor written in Kotlin
 *
 * This project aims to provide a simple, yet effective data pipeline monitoring system.
 * It will track the health and performance of various data pipelines in real-time,
 * providing insights and alerts to ensure data integrity and flow.
 *
 * Author: [Your Name]
 * Date: [Today's Date]
 */

import kotlin.collections.set

/**
 * DataPipeline represents a single data pipeline with its unique identifier, name, and status.
 */
data class DataPipeline(val id: Int, val name: String, var status: PipelineStatus)

/**
 * PipelineStatus is an enumeration representing the possible statuses of a data pipeline.
 */
enum class PipelineStatus {
    RUNNING, PAUSED, FAILED
}

/**
 * PipelineMonitor is the core class responsible for tracking and monitoring data pipelines.
 */
class PipelineMonitor {
    private val pipelines: MutableSet<DataPipeline> = mutableSetOf()

    /**
     * Adds a new data pipeline to the monitor.
     */
    fun addPipeline(pipeline: DataPipeline) {
        pipelines.add(pipeline)
    }

    /**
     * Removes a data pipeline from the monitor.
     */
    fun removePipeline(pipelineId: Int) {
        pipelines.removeIf { it.id == pipelineId }
    }

    /**
     * Updates the status of a data pipeline.
     */
    fun updatePipelineStatus(pipelineId: Int, newStatus: PipelineStatus) {
        pipelines.find { it.id == pipelineId }?.status = newStatus
    }

    /**
     * Retrieves the status of all monitored data pipelines.
     */
    fun getPipelineStatuses(): Map<Int, PipelineStatus> {
        return pipelines.associate { it.id to it.status }
    }
}

/**
 * Main function to demonstrate the usage of the PipelineMonitor.
 */
fun main() {
    val monitor = PipelineMonitor()

    // Add some sample pipelines
    monitor.addPipeline(DataPipeline(1, "Pipeline 1", PipelineStatus.RUNNING))
    monitor.addPipeline(DataPipeline(2, "Pipeline 2", PipelineStatus.PAUSED))
    monitor.addPipeline(DataPipeline(3, "Pipeline 3", PipelineStatus.FAILED))

    // Print initial pipeline statuses
    println("Initial pipeline statuses:")
    println(monitor.getPipelineStatuses())

    // Update a pipeline status
    monitor.updatePipelineStatus(2, PipelineStatus.RUNNING)

    // Print updated pipeline statuses
    println("Updated pipeline statuses:")
    println(monitor.getPipelineStatuses())
}