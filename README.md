# Cyano

Cyano is a testing module extracted from the Minestom fork Microtus, which has been abandoned for an indefinite period.
The reasons for this abandonment are complex and will not be discussed here.

## Overview

This project addresses the need for continued support of enhanced testing capabilities that were previously available in
a now-abandoned fork. While Minestom has its own testing module, the fork included several improvements and
quality-of-life features. Since some projects depend on these enhanced testing features, we've extracted them to this
standalone repository to prevent breaking changes.

Cyano preserves all functionality from the original enhanced testing module while incorporating relevant upstream
changes from Minestom. This gives you both the familiar features you depend on and the latest improvements from the main
repository.

## Getting Started

To use Cyano in your tests, you'll need to include it through your build system and use the appropriate annotation.

### Basic Test Setup

Integration tests with Cyano begin with this annotation:

```java
import net.minestom.testing.extension.MicrotusExtension;

@ExtendWith(MicrotusExtension.class)
class MyTestClass {
    // Your test methods here
}
```

The `MicrotusExtension` serves as the entry point for the testing module and follows JUnit 5's extension architecture.

> [!IMPORTANT]
> The `@ExtendWith` annotation must be placed above the class declaration, or the testing framework integration will
> fail.

## Enhanced Features

### Simplified Player Creation

Cyano includes quality-of-life improvements not found in the upstream Minestom testing module. You can now create
players without specifying spawn coordinates. The system will automatically use `(0, 0, 0)` as the default position.

```java
// Creates a player that spawns at coordinates (0, 0, 0)
Player player = env.createPlayer(instance);
```

This simplification is particularly useful in tests where player positioning is irrelevant to the functionality being
tested.

### Improved Test Cleanup

Managing test cleanup can be challenging, especially when dealing with instances that have active players. Minestom
prevents instance destruction while players remain connected, which can lead to frustrating test failures.

Cyano addresses this with an enhanced `destroyInstance()` method:

```java
// Automatically removes all players before destroying the instance
env.destroyInstance(instance, true);
```

When the boolean parameter is set to `true`, all players on the instance are automatically removed before destruction,
eliminating cleanup-related issues.

## Migration Guide

Migrating from the standard Minestom testing module to Cyano is straightforward:

1. **Change the annotation**: Replace your existing test extension annotation with
   `@ExtendWith(MicrotusExtension.class)`
2. **Optional enhancements**: The new features (simplified player creation and improved cleanup) are opt-in. If you
   don't use them explicitly, your existing tests will continue to work with their original behavior.

## Compatibility

Cyano maintains full backward compatibility with existing Minestom tests. Developers must explicitly use all enhanced
features. The default behavior remains unchanged, ensuring your existing test suites continue to work without
modification.