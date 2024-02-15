package sary.hei.api.file;

import sary.hei.api.PojaGenerated;

@PojaGenerated
public record FileHash(FileHashAlgorithm algorithm, String value) {}
